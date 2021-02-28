package org.sacc.smis.controller;

import io.swagger.annotations.ApiParam;
import org.sacc.smis.entity.Grades;
import org.sacc.smis.entity.UpdatePassword;
import org.sacc.smis.entity.User;
import org.sacc.smis.entity.UserRegisterParam;
import org.sacc.smis.model.RestResult;
import org.sacc.smis.model.UserInfo;
import org.sacc.smis.service.GradesService;
import org.sacc.smis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * Created by 林夕
 * Date 2021/1/19 20:19
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private GradesService gradesService;

    @ResponseBody
    @PostMapping("/register")
    public RestResult<Boolean> register(@RequestBody UserRegisterParam userRegisterParam) {
        return RestResult.success(userService.registerStudent(userRegisterParam));
    }

    @ResponseBody
    @PostMapping("/register/teacher")
    public RestResult<Boolean> registerTeacher(@RequestBody UserRegisterParam userRegisterParam) {
        return RestResult.success(userService.registerTeacher(userRegisterParam));
    }

    @ResponseBody
    @PostMapping("/register/admin")
    public RestResult<Boolean> registerAdmin(@RequestBody UserRegisterParam userRegisterParam) {
        return RestResult.success(userService.registerAdmin(userRegisterParam));
    }

    @ResponseBody
    @GetMapping("/findAll")
    public RestResult<List<User>> findAll() {
        return RestResult.success(userService.findAll());
    }


    /**
     * Authentication authentication 从session中拿到用户信息
     */
    @ResponseBody
    @PostMapping("/update")
    public RestResult<Boolean> update(@RequestBody User user, Authentication authentication) {
        UserInfo userInfo = (UserInfo) authentication.getPrincipal();
        user.setId(userInfo.getId());
        return RestResult.success(userService.updateInfo(user));
    }

    /**
     * 发送忘记密码邮件请求，每日申请次数不超过5次，每次申请间隔不低于 5分钟
     */

    @ResponseBody
    @PostMapping("/validate/sendValidationEmail")
    //通过邮箱找回密码
    public RestResult<String> sendValidationEmail(@ApiParam("邮箱地址") @RequestParam("email") String email,
                                                  HttpServletRequest request){
        int status = userService.sendValidationEmail(email,request);
        if(status!=0) return RestResult.error(503, "操作过于频繁,请在"+ status + "秒后再试!");
        return RestResult.success("发送成功");
    }

    /**
     * 将url的token和数据库里的token匹配，成功后便可修改密码，token有效期为60分钟
     */
    @ResponseBody
    @PostMapping("/validate/resetPassword")
    public RestResult<Boolean> resetPassword(@ApiParam("Token") @RequestParam("token") String token,
                                            @ApiParam("密码") @RequestParam("password") String password,
                                            @ApiParam("密码确认") @RequestParam("confirmPassword") String confirmPassword) {
        return RestResult.success(userService.resetPassword(token,password,confirmPassword));
    }


    @ResponseBody
    @GetMapping("/userInfo")
    @PreAuthorize("hasRole('STUDENT')")
    public RestResult<UserInfo> getUserInfo(Authentication authentication) {
        UserInfo userInfo = (UserInfo) authentication.getPrincipal();
        // 隐藏密码等敏感信息
        userInfo.setPassword("n/a");
        return RestResult.success(userInfo);
    }

    @ResponseBody
    @PostMapping("/updatePassword")
    public RestResult<Boolean> updatePassword(@RequestBody @Validated UpdatePassword updatePassword,
                                              BindingResult bindingResult,
                                              Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return RestResult.error(-1, Objects.requireNonNull(bindingResult.getFieldError()).getField() +
                    bindingResult.getFieldError().getDefaultMessage());
        }
        UserInfo userInfo = (UserInfo) authentication.getPrincipal();
        return RestResult.success(userService.updatePassword(updatePassword, userInfo.getId()));
    }

    @ResponseBody
    @PostMapping("/giveAuthority")
    public RestResult<Boolean> giveAuthority(@RequestParam("studentId") String studentId){
        return RestResult.success(userService.giveAuthority(studentId));
    }
}
