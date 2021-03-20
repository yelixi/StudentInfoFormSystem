package org.sacc.smis.entity.file;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by 林夕
 * Date 2021/3/20 16:54
 */
@Data
@Entity
public class SportsCompetitionFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "LONGBLOB")
    private byte[] dataSource;
}
