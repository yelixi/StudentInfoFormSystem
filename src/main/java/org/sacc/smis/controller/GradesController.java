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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Created by 林夕
 * Date 2021/2/22 9:59
 */

@Controller
public class GradesController {

    @Autowired
    private GradesService gradesService;

    @ResponseBody
    @GetMapping("/grades")
    @PreAuthorize("hasRole('STUDENT')")
    public RestResult<List<Grades>> getGrades(Authentication authentication){
        UserInfo userInfo = (UserInfo)authentication.getPrincipal();
        return RestResult.success(gradesService.findGradesByUserId(userInfo.getId()));
    }

    @ResponseBody
    @PostMapping("/uploadGrades")
    @PreAuthorize("hasRole('TEACHER')")
    public RestResult<Boolean> uploadGrades(@RequestBody @Validated Grades grades,
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

    @ResponseBody
    @PostMapping("/updateGrades")
    @PreAuthorize("hasRole('TEACHER')")
    public RestResult<Boolean> updateGrades(@RequestBody Grades grades,Authentication authentication){
        UserInfo userInfo = (UserInfo)authentication.getPrincipal();
        grades.setUserId(userInfo.getId());
        return RestResult.success(gradesService.uploadGrades(grades));
    }
}
