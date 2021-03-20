package org.sacc.smis.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
public class ComprehensiveTestForm {
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
     * 社会工作能力分数
     */
    @NotNull(message = "社会工作能力不能为空")
    private Integer socialWorkAbility;

    /**
     * 学科竞赛和科技竞赛分数
     */
    @NotNull(message = "学科竞赛和科技竞赛不能为空")
    private Integer subjectCompetition;

    /**
     * 学术科技成果分数
     */
    @NotNull(message = "学术科技成果不能为空")
    private Integer academicAndTechnologicalAchievements;

    /**
     * 大学英语国家考试分数
     */
    @NotNull(message = "大学英语国家考试不能为空")
    private Integer nationalCollegeEnglishTest;

    /**
     * 计算机能力分数
     */
    @NotNull(message = "计算机能力不能为空")
    private Integer computerAbility;

    /**
     * 文体比赛分数
     */
    @NotNull(message = "文体比赛不能为空")
    private Integer sportsCompetition;

    /**
     * 国际化能力分数
     */
    @NotNull(message = "国际化能力不能为空")
    private Integer internationalizationCapability;

    /**
     * 填表人id
     */
    private Integer userId;

    @CreatedDate
    private LocalDateTime createAt;

    @LastModifiedDate
    private LocalDateTime updateAt;

}
