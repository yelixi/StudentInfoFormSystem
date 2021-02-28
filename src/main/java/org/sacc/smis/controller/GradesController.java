package org.sacc.smis.controller;

import org.sacc.smis.entity.Grades;
import org.sacc.smis.model.RestResult;
import org.sacc.smis.model.UserInfo;
import org.sacc.smis.service.GradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Created by 林夕
 * Date 2021/2/22 9:59
 */

@Controller
@RequestMapping("/grades")
public class GradesController {

    @Autowired
    private GradesService gradesService;

    @ResponseBody
    @GetMapping("/getGrades")
    @PreAuthorize("hasAnyRole('STUDENT','MONITOR')")
    public RestResult<List<Grades>> getGrades(Authentication authentication){
        UserInfo userInfo = (UserInfo)authentication.getPrincipal();
        return RestResult.success(gradesService.findGradesByUserId(userInfo.getId()));
    }

    /**
     * 老师提交的学生的成绩(包含学生id)
     */
    @ResponseBody
    @PostMapping("/teacherUploadGrades")
    @PreAuthorize("hasAnyRole('TEACHER','MONITOR')")
    public RestResult<Boolean> teacherUploadGrades(@RequestBody @Validated Grades grades,
                                            BindingResult bindingResult,
                                            Authentication authentication){
        if(bindingResult.hasErrors()){
            return RestResult.error(-1, Objects.requireNonNull(bindingResult.getFieldError()).getField()+","+
                    bindingResult.getFieldError().getDefaultMessage());
        }
        UserInfo userInfo = (UserInfo)authentication.getPrincipal();
        grades.setUserId(userInfo.getId());
        return RestResult.success(gradesService.uploadGrades(grades));
    }

    /**
     * 学生自己提交的成绩
     */
    @ResponseBody
    @PostMapping("/studentUploadGrades")
    @PreAuthorize("hasAnyRole('STUDENT','MONITOR')")
    public RestResult<Boolean> studentUploadGrades(@RequestBody @Validated Grades grades,
                                            BindingResult bindingResult,
                                            Authentication authentication){
        if(bindingResult.hasErrors()){
            return RestResult.error(-1, Objects.requireNonNull(bindingResult.getFieldError()).getField()+","+
                    bindingResult.getFieldError().getDefaultMessage());
        }
        UserInfo userInfo = (UserInfo)authentication.getPrincipal();
        grades.setUserId(userInfo.getId());
        grades.setStudentId(userInfo.getId());
        return RestResult.success(gradesService.uploadGrades(grades));
    }

    @ResponseBody
    @PostMapping("/teacherUpdateGrades")
    @PreAuthorize("hasAnyRole('TEACHER','MONITOR')")
    public RestResult<Boolean> teacherUpdateGrades(@RequestBody Grades grades,Authentication authentication){
        UserInfo userInfo = (UserInfo)authentication.getPrincipal();
        grades.setUserId(userInfo.getId());
        return RestResult.success(gradesService.updateGrades(grades));
    }

}
