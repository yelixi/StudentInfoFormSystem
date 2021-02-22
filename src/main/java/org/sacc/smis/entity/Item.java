package org.sacc.smis.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Item {
    /**
     * 自增id
     */
    @Id
    @GeneratedValue
    private Integer id;
    /**
     * 项目名称对应的表单
     */
    @Column(nullable = false)
    private Integer applicationId;
    /**
     * 填写的项目名称,如：社会工作能力
     */
    @Column(nullable = false)
    private String name;

    @MapsId
    @OneToOne()
    @JoinColumn(name ="type_id")
    private ItemType type;


    /**
     * 班长给出的tag
     */
    private String monitorTag;

    /**
     * 班长id
     */
    private Integer monitorId;

    /**
     * 辅导员给出的tag
     */
    private String teacherTag;

    /**
     * 辅导员id
     */
    private Integer teacherId;

    /**
     * 填写人id
     */
    private Integer userId;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
