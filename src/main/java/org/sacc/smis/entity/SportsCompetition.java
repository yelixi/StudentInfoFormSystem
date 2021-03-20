package org.sacc.smis.entity;

import lombok.Data;
import org.sacc.smis.entity.file.SportsCompetitionFile;
import org.sacc.smis.entity.file.SubjectCompetitionFile;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by 林夕
 * Date 2021/3/17 14:26
 *
 * 文体竞赛表单
 */
@Data
@Entity
public class SportsCompetition {
    /**
     * 自增id
     */
    @Id
    @GeneratedValue
    private Integer id;
    /**
     * 名称
     */
    @Column(nullable = false)
    private String name;

    /**
     * 填写人id
     */
    private Integer userId;

    /**
     * 竞赛类别(国家级等)
     */
    @NotNull(message = "奖项级别不能为空")
    private String category;

    /**
     * 奖项等级(一等奖等)
     */
    @NotNull(message = "奖项等级不能为空")
    private String awardLevel;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 状态(已审核,审核中)
     */
    private Integer state;

    /**
     * 比赛时间
     */
    @NotNull(message = "比赛时间不能为空")
    private LocalDateTime competitionTime;

    /**
     * 证明材料
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "file_id")
    private List<SportsCompetitionFile> file;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
