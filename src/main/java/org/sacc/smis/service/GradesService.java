package org.sacc.smis.service;

import org.sacc.smis.entity.Grades;

import java.util.List;

/**
 * Created by 林夕
 * Date 2021/2/22 9:33
 */
public interface GradesService {
    List<Grades> findGradesByUserId(Integer userId);

    boolean uploadGrades(Grades grades);

    boolean updateGrades(Grades grades);
}
