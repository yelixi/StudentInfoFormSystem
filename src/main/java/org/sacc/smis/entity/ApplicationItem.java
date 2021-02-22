package org.sacc.smis.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by 林夕
 * Date 2021/1/19 21:33
 */
@Entity
@Data
public class ApplicationItem {
    @Id
    private Integer applicationId;

    @Column(nullable = false)
    private Integer itemId;
}
