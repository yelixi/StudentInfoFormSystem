package org.sacc.smis.mapper;

import org.sacc.smis.entity.UserValidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ValidateRepository extends JpaRepository<UserValidate, Integer> {
    @Query("select u from UserValidate u where u.resetToken = :token")
    List<UserValidate> findByToken(@Param("token") String token);

    @Query("select u from UserValidate u where u.email = :email")
    List<UserValidate> findByEmail(@Param("email") String email);
}
