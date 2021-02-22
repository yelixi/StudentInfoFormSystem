package org.sacc.smis.mapper;

import org.sacc.smis.entity.StudentMoralEducationItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 林夕
 * Date 2021/2/17 13:24
 */
public interface StudentMoralEducationRepository extends JpaRepository<StudentMoralEducationItem,Integer> {

    StudentMoralEducationItem findByUserIdAndType(Integer userId,boolean type);

    StudentMoralEducationItem findByUserIdAndCommentatorId(Integer userId,Integer commentatorId);
}
