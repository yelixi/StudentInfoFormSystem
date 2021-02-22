package org.sacc.smis.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by 林夕
 * Date 2021/2/4 18:34
 */
@Data
public class UpdatePassword {
    @NotNull(message = "旧密码不能为空")
    private String oldPassword;

    @NotNull(message = "新密码不能为空")
    private String newPassword;
}
