package org.sacc.smis;

import org.junit.jupiter.api.Test;
import org.sacc.smis.entity.User;
import org.sacc.smis.entity.UserRegisterParam;
import org.sacc.smis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ApplicationTest {

    @Autowired
    UserService userService;


    @Test
    public void testRegister(){
        String testEmail = "1807886910@fox.mail";
        UserRegisterParam userRegisterParam = new UserRegisterParam();
        userRegisterParam.setPassword("123132");
        userRegisterParam.setEmail(testEmail);
        userRegisterParam.setStudentId("B19040229");
        userService.register(userRegisterParam);
    }

    @Test
    public void testFindByEmail(){
        String testEmail = "1807886910@fox.mail";
        User userByEmail = userService.findUserByEmail(testEmail);
        System.out.println(userByEmail.toString());
    }

    @Test
    public void testFindByStudentId(){
        System.out.println(  userService.findUserByStudentId("B19040229").toString());
    }

}
