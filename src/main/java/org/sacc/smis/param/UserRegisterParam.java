package org.sacc.smis.param;

import lombok.Data;

/**
 * Created by 林夕
 * Date 2021/1/19 20:21
 */
@Data
public class UserRegisterParam {
    private String schoolNumber;

    private String password;

    private String email;
}
