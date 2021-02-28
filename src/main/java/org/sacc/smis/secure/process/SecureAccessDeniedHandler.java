package org.sacc.smis.secure.process;

import org.sacc.smis.model.RestResult;
import org.sacc.smis.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Describe: 自定义 Security 用户暂无权限处理类
 * @Author: tyf
 * @CreateTime: 2021-02-28
 **/
@Component
public class SecureAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseUtil.restResponse(response, HttpStatus.FORBIDDEN, RestResult.error(403, "抱歉，你当前的身份无权访问"));
    }
}
