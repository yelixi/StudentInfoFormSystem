package org.sacc.smis.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(
        indexes = {
                @Index(name = "email", columnList = "email",unique = true),
                @Index(name = "schoolNumber", columnList = "schoolNumber", unique = true)
        }
)
@EntityListeners(AuditingEntityListener.class)
public class User {
    /**
     * 自增id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String schoolNumber;
    @Column(nullable = false)
    private String role = "0";
    /**
     * 学院
     */
    //@Column(nullable = false)
    private String college;
    /**
     * 年级
     */
    //@Column(nullable = false)
    private Integer grade;
    /**
     * 专业
     */
    //@Column(nullable = false)
    private String profession;
    /**
     * 身份证
     */
    //@Column(nullable = false)
    private String idCord;

    /**
     * 班级
     */
    private String classes;

    /**
     * 辅导员所管班级
     */
    @Transient
    private List<String> classManagement;

    private String isFailed = "否";

    /**
     * 平均绩点
     */
    private Double averageGrades;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
