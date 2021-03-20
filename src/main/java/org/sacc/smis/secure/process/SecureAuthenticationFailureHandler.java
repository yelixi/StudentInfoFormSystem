package org.sacc.smis.secure.process;

import org.sacc.smis.exception.GlobalExceptionHandler;
import org.sacc.smis.model.RestResult;
import org.sacc.smis.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Describe: 自定义 Security 用户登录异常类
 * @Author: tyf
 * @CreateTime: 2021-02-28
 **/
@Component
public class SecureAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                     AuthenticationException exception) throws IOException, ServletException {
        RestResult<String> result = new RestResult<>(403, "登录失败!");
        if (exception instanceof UsernameNotFoundException){
            result.setMessage("用户名不存在!");
        }
        if (exception instanceof BadCredentialsException){
            result.setMessage("账户密码不正确");
        }
        if (exception instanceof DisabledException){
            result.setMessage("用户未启用");
        }
        logger.error(result.getMessage(), exception);
        ResponseUtil.restResponse(response,  HttpStatus.FORBIDDEN, result);


    }
}
