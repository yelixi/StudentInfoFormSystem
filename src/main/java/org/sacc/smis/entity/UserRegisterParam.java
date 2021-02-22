package org.sacc.smis.entity;

import lombok.Data;

/**
 * Created by 林夕
 * Date 2021/1/19 20:21
 */
@Data
public class UserRegisterParam {
    private String studentId;

    private String password;

    private String email;
}
