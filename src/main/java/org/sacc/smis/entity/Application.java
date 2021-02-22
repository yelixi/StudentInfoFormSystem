package org.sacc.smis.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 表单实体
 */
@Entity
@Data
public class Application {
    /**
     * 自增id
     */
    @Id
    @GeneratedValue
    private Integer id;
    /**
     * 表单名称,如2019级软件工程学院学生综合素质评价自我评议及班级/年级考评表
     */
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer userId;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
