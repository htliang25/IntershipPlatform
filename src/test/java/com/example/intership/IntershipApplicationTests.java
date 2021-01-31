package com.example.intership;

import com.example.intership.controller.ModifyPwdController;
import com.example.intership.controller.ShowController;
import com.example.intership.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class IntershipApplicationTests {
    @Autowired
    ShowController showController;

    @Test
    void contextLoads() {
        Map map = showController.getUser("aa", 1);
    }
}
