package org.sacc.smis.service;

import org.junit.jupiter.api.Test;
import org.sacc.smis.entity.SubjectCompetition;
import org.sacc.smis.entity.file.SubjectCompetitionFile;
import org.sacc.smis.repository.SubjectCompetitionRepository;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by 林夕
 * Date 2021/3/20 15:36
 */
@SpringBootTest
class SubjectCompetitionFileServiceTest {

    @Resource
    private SubjectCompetitionFileService subjectCompetitionFileService;

    @Resource
    private SubjectCompetitionRepository subjectCompetitionRepository;

    @Test
    void save() {
        SubjectCompetition subjectCompetition = subjectCompetitionRepository.getOne(5);

        SubjectCompetitionFile file1 = new SubjectCompetitionFile();
        SubjectCompetitionFile file2 = new SubjectCompetitionFile();
        SubjectCompetitionFile file3 = new SubjectCompetitionFile();
        byte[] bytes1 = "Hello world1".getBytes();
        byte[] bytes2 = "Hello world2".getBytes();
        byte[] bytes3 = "Hello world3".getBytes();

        file1.setDataSource(bytes1);
        file2.setDataSource(bytes2);
        file3.setDataSource(bytes3);

    }
}
