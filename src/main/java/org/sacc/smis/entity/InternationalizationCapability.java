package org.sacc.smis.entity;

import lombok.Data;
import org.sacc.smis.entity.file.InternationalizationCapabilityFile;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by 林夕
 * Date 2021/3/17 14:27
 *
 * 国际化能力
 */
@Data
@Entity
public class InternationalizationCapability {

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 所访学校
     */
    private String visitingSchools;

    /**
     * 访问时间
     */
    private LocalDateTime visitingTime;

    /**
     * 所访学校排名
     */
    private Integer ranking;

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
    private List<InternationalizationCapabilityFile> file;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
