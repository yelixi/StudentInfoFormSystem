package org.sacc.smis.mapper;

import org.sacc.smis.entity.Grades;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 林夕
 * Date 2021/2/22 9:38
 */
public interface GradesRepository extends JpaRepository<Grades,Integer> {

    List<Grades> findAllByUserId(Integer userId);

    Grades getByStudentIdAndGradesName(Integer studentId,String gradesName);
}
