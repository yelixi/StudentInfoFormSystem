package org.sacc.smis.service.impl;

import org.sacc.smis.entity.SubjectCompetition;
import org.sacc.smis.repository.SubjectCompetitionRepository;
import org.sacc.smis.service.SubjectCompetitionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 林夕
 * Date 2021/3/18 17:03
 */
@Service
public class SubjectCompetitionServiceImpl implements SubjectCompetitionService {

    @Resource
    private SubjectCompetitionRepository subjectCompetitionRepository;

    @Override
    public void saveSubjectCompetition(SubjectCompetition subjectCompetition) {
        subjectCompetitionRepository.save(subjectCompetition);
    }

    @Override
    public SubjectCompetition findSubjectCompetitionById(Integer id) {
        return subjectCompetitionRepository.findById(id).get();
    }
}
