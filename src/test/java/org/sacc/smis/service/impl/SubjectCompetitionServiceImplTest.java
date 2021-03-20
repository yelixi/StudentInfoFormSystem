package org.sacc.smis.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.sacc.smis.entity.SubjectCompetition;
import org.sacc.smis.entity.file.SubjectCompetitionFile;
import org.sacc.smis.service.SubjectCompetitionService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by 林夕
 * Date 2021/3/18 17:07
 */
@Slf4j
@SpringBootTest
class SubjectCompetitionServiceImplTest {

    @Resource
    private SubjectCompetitionService subjectCompetitionService;

    @Test
    void saveSubjectCompetition() {
        SubjectCompetition subjectCompetition = new SubjectCompetition();
        subjectCompetition.setName("竞赛A");
        subjectCompetition.setCategory("国家级");
        subjectCompetition.setLevel("一等奖");
        subjectCompetition.setCompetitionTime(LocalDateTime.now());

        List<SubjectCompetitionFile> list = new ArrayList<>();

        SubjectCompetitionFile file1 = new SubjectCompetitionFile();
        SubjectCompetitionFile file2 = new SubjectCompetitionFile();
        SubjectCompetitionFile file3 = new SubjectCompetitionFile();
        byte[] bytes1 = "Hello world1".getBytes();
        byte[] bytes2 = "Hello world2".getBytes();
        byte[] bytes3 = "Hello world3".getBytes();

        file1.setDataSource(bytes1);
        file2.setDataSource(bytes2);
        file3.setDataSource(bytes3);

        list.add(file1);
        list.add(file2);
        list.add(file3);

        subjectCompetition.setFile(list);

        subjectCompetitionService.saveSubjectCompetition(subjectCompetition);
    }

    @Test
    void findSubjectCompetitionById(){
        SubjectCompetition subjectCompetition = subjectCompetitionService.findSubjectCompetitionById(1);
        System.out.println("------------------------------------------");
        log.info(subjectCompetition.toString());
    }
}
