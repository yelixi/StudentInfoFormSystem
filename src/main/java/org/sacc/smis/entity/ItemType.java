package org.sacc.smis.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class ItemType {
    /**
     * 自增id
     */
    @Id
    @GeneratedValue
    private Integer id;
    /**
     * 类型名称，即具体的获奖内容
     */
    @Column(nullable = false)
    private String name;
    /**
     * 预制或选择的数据来源
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "LONGBLOB")
    private byte[] dataSource;
    /**
     * 校验规则
     */
    private String validRule;

    /**
     * 填写人id
     */
    private Integer userId;

    /**
     * 上传的文件的后缀(含有"."号的)
     */
    private String fileSuffix;

    @OneToOne(mappedBy ="type", cascade = CascadeType.ALL)
    private Item item;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
