package org.sacc.smis.util;

import org.junit.jupiter.api.Test;
import org.sacc.smis.entity.User;
import org.springframework.beans.BeanUtils;

public class RequstTest {

    @Test
    public void copyProperties(){
        User user1 = new User();
        User user2 = new User();
        user1.setId(10);
        user2.setId(20);
        user2.setPassword("100101");
        user1.setEmail("19808090@fqfq.com");
        BeanUtils.copyProperties(user1,user2);
        System.out.println(user2.toString());

    }
}
