package org.sacc.smis.service;

import org.sacc.smis.entity.UpdatePassword;
import org.sacc.smis.entity.Item;
import org.sacc.smis.entity.User;
import org.sacc.smis.entity.UserRegisterParam;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 林夕
 * Date 2021/1/19 22:02
 */
public interface UserService {
    List<User> findAll();

    boolean register(UserRegisterParam userRegisterParam);

    boolean updateInfo(User user);

    boolean updatePassword(UpdatePassword updatePassword, Integer userId);

    User findUserByEmail(String email);

    User findUserByStudentId(String studentId);

    boolean updatePassword(Integer userId, String password);

    Integer sendValidationEmail(String email, HttpServletRequest request);

    boolean resetPassword(String token,String password,String confirmPassword);
}
