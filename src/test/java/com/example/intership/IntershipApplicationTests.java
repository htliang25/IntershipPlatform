package com.example.intership;

import com.example.intership.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IntershipApplicationTests {
    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        String name = "aa";
        String pwd = "123456";
        int role = 1;
        userService.saveUser(name, pwd, role);
    }

}
