package org.sacc.smis.secure.process;

import org.sacc.smis.model.RestResult;
import org.sacc.smis.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Describe: 自定义 Security 用户未登陆处理类
 * @Author: tyf
 * @CreateTime: 2021-02-28
 **/
@Component
public class SecureAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseUtil.restResponse(response, HttpStatus.UNAUTHORIZED, RestResult.error(401, "请先登录"));
    }
}
