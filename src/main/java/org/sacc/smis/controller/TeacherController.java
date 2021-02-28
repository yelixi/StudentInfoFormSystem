package org.sacc.smis.controller;

import org.sacc.smis.entity.Application;
import org.sacc.smis.model.RestResult;
import org.sacc.smis.model.UserInfo;
import org.sacc.smis.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@Secured("ROLE_TEACHER")
public class TeacherController {

    @Autowired
    ApplicationService applicationService;

    @ResponseBody
    @GetMapping("/getApplication")
    public RestResult<Application> getApplication(@RequestParam("applicationId") Integer applicationId) {
        return RestResult.success(applicationService.getApplicationById(applicationId));
    }

    @ResponseBody
    @PostMapping("/addApplication")
    public RestResult<Boolean> addApplication(@Validated @RequestBody Application application,
                                              BindingResult bindingResult, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return RestResult.error(-1, Objects.requireNonNull(bindingResult.getFieldError()).getField() +
                    bindingResult.getFieldError().getDefaultMessage());
        }
        UserInfo userInfo = (UserInfo) authentication.getPrincipal();
        application.setUserId(userInfo.getId());
        return RestResult.success(applicationService.addApplication(application));
    }

    @ResponseBody
    @PostMapping("/deleteApplication/{application_id}")
    public RestResult<Boolean> deleteApplication(@PathVariable("application_id") Integer applicationId) {
        return RestResult.success(applicationService.deleteApplication(applicationId));
    }

    @ResponseBody
    @PostMapping("/updateApplication")
    public RestResult<Boolean> updateApplication(@RequestBody Application application,
                                                 Authentication authentication) {
        UserInfo userInfo = (UserInfo)authentication.getCredentials();
        application.setUserId(userInfo.getId());
        return RestResult.success(applicationService.updateApplication(application));
    }

    @ResponseBody
    @GetMapping("/application/all")
    public RestResult<List<Application>> findAllApplication() {
        return RestResult.success(applicationService.findAllApplications());
    }

}
