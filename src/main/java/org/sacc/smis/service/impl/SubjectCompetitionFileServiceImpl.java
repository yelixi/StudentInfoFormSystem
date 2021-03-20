package org.sacc.smis.service.impl;

import org.sacc.smis.entity.file.SubjectCompetitionFile;
import org.sacc.smis.repository.SubjectCompetitionFileRepository;
import org.sacc.smis.service.SubjectCompetitionFileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 林夕
 * Date 2021/3/20 15:29
 */
@Service
public class SubjectCompetitionFileServiceImpl implements SubjectCompetitionFileService {

    @Resource
    private SubjectCompetitionFileRepository subjectCompetitionFileRepository;

    @Override
    public void save(SubjectCompetitionFile subjectCompetitionFile) {
        subjectCompetitionFileRepository.save(subjectCompetitionFile);
    }
}
