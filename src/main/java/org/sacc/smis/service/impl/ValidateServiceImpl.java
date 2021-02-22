package org.sacc.smis.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import org.sacc.smis.entity.User;
import org.sacc.smis.entity.UserValidate;
import org.sacc.smis.mapper.ValidateRepository;
import org.sacc.smis.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class ValidateServiceImpl implements ValidateService {
    @Autowired
    private JavaMailSenderImpl sender;

    @Autowired
    private ValidateRepository validateRepository;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送邮件
     *
     * @param email
     */
    @Override
    public void sendPasswordResetEmail(SimpleMailMessage email) {
        sender.send(email);
    }

    /**
     * 插入一条validate记录
     *
     * @param validate
     * @param users
     * @param token
     * @return
     */
    @Override
    public UserValidate insertNewResetRecord(UserValidate validate, User users, String token) {
        validate.setUserId(users.getId());
        validate.setEmail(users.getEmail());
        validate.setResetToken(token);
        return validateRepository.save(validate);
    }

    /**
     * 通过Token查找申请记录
     *
     * @param resetToken
     * @return
     */
    @Override
    public List<UserValidate> findUserByResetToken(String resetToken) {
        return validateRepository.findByToken(resetToken);
    }

    /**
     * 验证连接是否失效：链接有两种情况失效 1.超时 2.最近请求的一次链接自动覆盖之前的链接（待看代码）
     *
     * @param email
     * @param requestPerDay
     * @param interval    生效间隔
     * @param token
     */
    @Override
    public boolean validateLimitation(String email, long requestPerDay, long interval, String token) {
        List<UserValidate> validates = validateRepository.findByEmail(email);
        Optional<Date> validate = validates.stream().map(UserValidate::getCreatedAt).max(Date::compareTo);
        Date dateOfLastRequest = new Date();
        if (validate.isPresent()) dateOfLastRequest = validate.get();
        long intervalTime = new Date().getTime() - dateOfLastRequest.getTime();
        //判断是否为最近Token 通过日期判断
        Optional<Date> ofLastRequestToken = validates.stream().filter(x -> x.getResetToken().equals(token)).map(UserValidate::getCreatedAt).findAny();
        Date dateOfLastToken = new Date();
        if (ofLastRequestToken.isPresent()) dateOfLastToken = ofLastRequestToken.get();
        return intervalTime <= interval * 60 * 1000 && dateOfLastRequest == dateOfLastToken;
    }

    /**
     * 验证是否发送重置邮件：每个email的重置密码每日请求上限为requestPerDay次，与上一次的请求时间间隔为interval分钟。
     *
     * @param email         邮箱地址
     * @param requestPerDay 次数上限
     * @param interval      间隔时间
     * @return boolean
     */
    @Override
    public long sendValidateLimitation(String email, long requestPerDay, int interval) {
        List<UserValidate> validates = validateRepository.findByEmail(email);
        //没有记录,直接通行
        if (validates.isEmpty()) {
            return 0;
        }
        //获得同一天请求次数
        long countTodayValidation = validates.stream().filter(x -> DateUtil.isSameDay(x.getCreatedAt(), new Date())).count();
        Optional validate = validates.stream().map(UserValidate::getCreatedAt).max(Date::compareTo);
        Date dateOfLastRequest = new Date();
        if (validate.isPresent()) dateOfLastRequest = (Date) validate.get();
        long intervalForLastRequest = new Date().getTime() - dateOfLastRequest.getTime();
        //判断是否小于上限以及间隔大于Interval(分钟)
        if (countTodayValidation >= requestPerDay){
            return -1;
        }else if (intervalForLastRequest <= (long) interval * 60 * 1000){
            return DateUtil.between(new Date(),DateUtil.offset(dateOfLastRequest, DateField.MINUTE,interval), DateUnit.SECOND);

        }else{
            return 0;
        }
    }
}
