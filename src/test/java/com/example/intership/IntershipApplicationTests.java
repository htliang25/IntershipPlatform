package com.example.intership;

import com.example.intership.dao.UserTemplate;
import com.example.intership.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IntershipApplicationTests {
    @Autowired
    UserTemplate userTemplate;

    @Test
    void contextLoads(){
        User user = userTemplate.getUser("bb", 1);
        System.out.println();
    }
}
