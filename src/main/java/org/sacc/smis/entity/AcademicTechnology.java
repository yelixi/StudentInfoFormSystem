package org.sacc.smis.entity;

import lombok.Data;
import org.sacc.smis.entity.file.AcademicTechnologyFile;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by 林夕
 * Date 2021/3/17 14:25
 *
 * 学术科技成果表单
 */

@Entity
@Data
public class AcademicTechnology {

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 类别
     */
    @NotNull(message = "")
    private String category;

    /**
     * 名称
     */
    @NotNull(message = "")
    private String name;

    /**
     * 比赛时间
     */
    @NotNull(message = "")
    private LocalDateTime competitionTime;

    /**
     * 级别
     */
    @NotNull(message = "")
    private String level;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 填写人id
     */
    private Integer userId;

    /**
     * 证明材料
     */
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "file_id")
    private List<AcademicTechnologyFile> file;

    @CreatedDate
    private LocalDateTime CreateAt;

    @LastModifiedDate
    private LocalDateTime updateAt;
}
