package org.sacc.smis.entity.file;

import lombok.Data;
import org.sacc.smis.entity.SubjectCompetition;

import javax.persistence.*;

/**
 * Created by 林夕
 * Date 2021/3/18 16:38
 */
@Entity
@Data
@Table(name = "subject_competition_file")
public class SubjectCompetitionFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "LONGBLOB")
    private byte[] dataSource;
}
