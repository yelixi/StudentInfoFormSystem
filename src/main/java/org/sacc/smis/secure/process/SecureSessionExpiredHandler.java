package org.sacc.smis.secure.process;

import org.sacc.smis.model.RestResult;
import org.sacc.smis.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @Describe: 自定义 Security 检测过期会话处理
 * @Author: tyf
 * @CreateTime: 2021-02-28
 **/
@Component
public class SecureSessionExpiredHandler implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        ResponseUtil.restResponse(event.getResponse(), HttpStatus.FORBIDDEN, RestResult.error(499, "您的账号在别的地方登录，当前登录已失效"));

    }
}
