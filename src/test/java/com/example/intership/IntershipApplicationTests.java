package com.example.intership;

import com.example.intership.controller.ModifyPwdController;
import com.example.intership.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class IntershipApplicationTests {
    @Autowired
    ModifyPwdController modifyPwdController;

    @Test
    void contextLoads() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", "aa");
        map.put("newPassword", "123456");
        map.put("oldPassword", "654321");
        map.put("role", 2);
        Map<String, Object> res = modifyPwdController.modifyPwd(map);
    }
}
