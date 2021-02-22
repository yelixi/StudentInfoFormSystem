package org.sacc.smis.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by 林夕
 * Date 2021/2/9 15:12
 */
@Data
@Entity
public class StudentMoralEducationItem {
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 政治思想分数
     */
    @NotNull(message = "政治思想分数不能为空")
    private Integer politicalIdeology;

    /**
     * 道德品质分数
     */
    @NotNull(message = "道德品质分数不能为空")
    private Integer moralQuality;

    /**
     * 法纪观念分数
     */
    @NotNull(message = "法纪观念分数不能为空")
    private Integer legalConcept;

    /**
     * 学习态度分数
     */
    @NotNull(message = "学习态度分数不能为空")
    private Integer learningAttitude;

    /**
     * 集体观念分数
     */
    @NotNull(message = "集体观念分数不能为空")
    private Integer collectiveIdea;

    /**
     * 文化素养分数
     */
    @NotNull(message = "文化素养分数不能为空")
    private Integer culturalLiteracy;

    /**
     * 安全卫生分数
     */
    @NotNull(message = "安全卫生分数不能为空")
    private Integer healthAndSafety;

    /**
     * 填表人id
     */
    private Integer userId;

    /**
     * 评写人id
     */
    private Integer commentatorId;

    /**
     * false表示未评写,true表示已评写
     */
    private boolean type = false;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
}
