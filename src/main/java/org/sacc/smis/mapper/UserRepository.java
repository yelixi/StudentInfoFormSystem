package org.sacc.smis.mapper;

import org.sacc.smis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

/**
 * Created by 林夕
 * Date 2021/1/19 20:15
 */


public interface UserRepository extends JpaRepository<User, Integer> {
    User findByStudentId(String studentId);

    User findByEmail(String email);

    @Query("select u from User u where u.id = :id")
    User findByPrimaryKey(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("update User u set u.password= :password where u.id= :id")
    void updatePassword(@Param("id") Integer id, @Param("password") String password);

}
