package org.sacc.smis.service.impl;

import org.sacc.smis.entity.StudentMoralEducationItem;
import org.sacc.smis.mapper.StudentMoralEducationRepository;
import org.sacc.smis.service.StudentMoralEducationService;
import org.sacc.smis.util.GetNullPropertyNamesUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Created by 林夕
 * Date 2021/2/17 13:24
 */
@Service
public class StudentMoralEducationServiceImpl implements StudentMoralEducationService {

    @Autowired
    private StudentMoralEducationRepository studentMoralEducationRepository;

    @Override
    public boolean uploadItem(StudentMoralEducationItem item) {
        LocalDateTime time = LocalDateTime.now();
        item.setCreateAt(time);
        item.setUpdateAt(time);
        studentMoralEducationRepository.save(item);
        return true;
    }

    @Override
    public boolean updateItem(StudentMoralEducationItem item) {
        StudentMoralEducationItem i = studentMoralEducationRepository.
                findByUserIdAndType(item.getUserId(), item.getType());
        BeanUtils.copyProperties(item, i, GetNullPropertyNamesUtil.getNullPropertyNames(item));
        studentMoralEducationRepository.save(i);
        return true;
    }

    @Override
    public boolean updateCommentItem(StudentMoralEducationItem item) {
        StudentMoralEducationItem i = studentMoralEducationRepository.
                findByUserIdAndCommentatorId(item.getUserId(), item.getCommentatorId());
        BeanUtils.copyProperties(item, i, GetNullPropertyNamesUtil.getNullPropertyNames(item));
        studentMoralEducationRepository.save(i);
        return true;
    }
}
