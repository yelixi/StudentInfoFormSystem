package org.sacc.smis.service;

import org.sacc.smis.entity.SubjectCompetition;

/**
 * Created by 林夕
 * Date 2021/3/18 17:03
 */
public interface SubjectCompetitionService {

    void saveSubjectCompetition(SubjectCompetition subjectCompetition);

    SubjectCompetition findSubjectCompetitionById(Integer id);
}
