package org.sacc.smis.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by 林夕
 * Date 2021/2/1 18:11
 */
@Data
@Entity
public class Grades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer schoolNumber;

    /**
     * 某科成绩名称，如：竞赛成绩，体育成绩，体测成绩
     */
    @Column(nullable = false)
    @NotNull(message = "科目名称不能为空")
    private String gradesName;

    /**
     * 具体的分数
     */
    @Column(nullable = false)
    @NotNull(message = "分数不能为空")
    private Double grade;

    /**
     * 填写人id(以最后一次更新成绩为准)
     */
    private Integer userId;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;
}
