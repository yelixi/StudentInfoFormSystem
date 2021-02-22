package org.sacc.smis.mapper;

import org.sacc.smis.entity.Application;
import org.sacc.smis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    @Query("select a from Application a where a.id = :id")
    Application findByPrimaryKey(@Param("id") Integer id);
}
