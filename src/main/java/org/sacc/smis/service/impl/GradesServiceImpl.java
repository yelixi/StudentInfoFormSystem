package org.sacc.smis.service.impl;

import org.sacc.smis.entity.Grades;
import org.sacc.smis.mapper.GradesRepository;
import org.sacc.smis.service.GradesService;
import org.sacc.smis.util.GetNullPropertyNamesUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by 林夕
 * Date 2021/2/22 9:33
 */
@Service
public class GradesServiceImpl implements GradesService {

    @Autowired
    private GradesRepository gradesRepository;

    @Override
    public List<Grades> findGradesByUserId(Integer userId) {
        return gradesRepository.findAllByUserId(userId);
    }

    @Override
    public boolean uploadGrades(Grades grades) {
        LocalDateTime time = LocalDateTime.now();
        grades.setUpdateAt(time);
        grades.setCreateAt(time);
        gradesRepository.save(grades);
        return true;
    }

    @Override
    public boolean updateGrades(Grades grades) {
        Grades g = gradesRepository.getByStudentIdAndGradesName(grades.getStudentId(), grades.getGradesName());
        BeanUtils.copyProperties(grades, g, GetNullPropertyNamesUtil.getNullPropertyNames(grades));
        gradesRepository.save(g);
        return true;
    }
}
