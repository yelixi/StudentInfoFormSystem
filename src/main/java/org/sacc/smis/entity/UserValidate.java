package org.sacc.smis.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/*
 * 用以验证用户身份,实现密码找回等功能;
 * */
@Entity
@Data
@Table
@EntityListeners(AuditingEntityListener.class)
public class UserValidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /*
     * 用户ID
     * */
    @Column(nullable = false)
    private Integer userId;

    /*
     * 用户Email
     * */
    @Column(nullable = false)
    private String email;

    /*
     *用户Token
     */
    @Column(nullable = false)
    private String resetToken;

    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;
}
