package org.sacc.smis.controller;

import io.swagger.annotations.Authorization;
import org.sacc.smis.entity.StudentMoralEducationItem;
import org.sacc.smis.model.RestResult;
import org.sacc.smis.model.UserInfo;
import org.sacc.smis.service.StudentMoralEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * Created by 林夕
 * Date 2021/2/17 10:49
 */
@Controller
@RequestMapping("/studentMoralEducationItem")
public class StudentMoralEducationItemController {

    @Autowired
    private StudentMoralEducationService studentMoralEducationService;


    /**
     * 学生填写德育自评部分的表单上传
     * @param item 德育自评表单
     * @param bindingResult 表单校验
     * @param authentication session
     * @return boolean
     */
    @ResponseBody
    @PreAuthorize("hasAnyRole('STUDENT','MONITOR')")
    @PostMapping("/uploadItem")
    public RestResult<Boolean> uploadItem(@Validated @RequestBody StudentMoralEducationItem item,
                                          BindingResult bindingResult,
                                          Authentication authentication){
        if(bindingResult.hasErrors()){
            return RestResult.error(-1, Objects.requireNonNull(bindingResult.getFieldError()).getField()+","+
                    bindingResult.getFieldError().getDefaultMessage());
        }
        UserInfo userInfo = (UserInfo)authentication.getPrincipal();
        item.setUserId(userInfo.getId());
        return RestResult.success(studentMoralEducationService.uploadItem(item));
    }


    /**
     * 更新表单接口
     * @param item 重新填写的表单
     * @param authentication session
     * @return boolean
     */
    @ResponseBody
    @PreAuthorize("hasAnyRole('STUDENT','MONITOR')")
    @PostMapping("/updateItem")
    public RestResult<Boolean> updateItem(@RequestBody StudentMoralEducationItem item,
                                          Authentication authentication){
        UserInfo userInfo = (UserInfo)authentication.getPrincipal();
        item.setUserId(userInfo.getId());
        return RestResult.success(studentMoralEducationService.updateItem(item));
    }

    /**
     * 班长评议提交表单
     * @param item 表单(含有userId项)
     */
    @ResponseBody
    @PreAuthorize("hasAnyRole('MONITOR','TEACHER')")
    @PostMapping("/commentItem")
    public RestResult<Boolean> commentItem(@Validated @RequestBody StudentMoralEducationItem item,
                                           BindingResult bindingResult,
                                           Authentication authentication){
        if(bindingResult.hasErrors()){
            return RestResult.error(-1, Objects.requireNonNull(bindingResult.getFieldError()).getField()+","+
                    bindingResult.getFieldError().getDefaultMessage());
        }
        UserInfo userInfo = (UserInfo)authentication.getPrincipal();
        item.setCommentatorId(userInfo.getId());
        item.setType(true);
        return RestResult.success(studentMoralEducationService.uploadItem(item));
    }

    @ResponseBody
    @PreAuthorize("hasAnyRole('MONITOR','TEACHER')")
    @PostMapping("/updateCommentItem")
    public RestResult<Boolean> updateCommentItem(@RequestBody StudentMoralEducationItem item,
                                          Authentication authentication){
        UserInfo userInfo = (UserInfo)authentication.getPrincipal();
        item.setCommentatorId(userInfo.getId());
        return RestResult.success(studentMoralEducationService.updateCommentItem(item));
    }
}
