package org.sacc.smis.util;

import org.junit.jupiter.api.Test;
import org.sacc.smis.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;

@SpringBootTest
public class MailTest {
    @Autowired
    private ValidateService validateService;

    @Test
    public void mailSender(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo("tan13621251388@gmail.com");
        simpleMailMessage.setFrom("1807886910@qq.com");
        simpleMailMessage.setSubject("test");
        simpleMailMessage.setText("test");
        validateService.sendPasswordResetEmail(simpleMailMessage);


    }
}
