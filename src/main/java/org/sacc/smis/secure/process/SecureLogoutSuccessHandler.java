package org.sacc.smis.secure.process;

import org.sacc.smis.model.RestResult;
import org.sacc.smis.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Describe: 自定义用户注销成功处理类
 * @Author: tyf
 * @CreateTime: 2021-02-28
 **/
@Component
public class SecureLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ResponseUtil.restResponse(response, HttpStatus.OK, RestResult.error(200, "注销成功"));
    }
}
