package com.example.intership.controller;

import com.example.intership.entities.user.User;
import com.example.intership.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @ResponseBody
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, Object> data) {
        String name = (String) data.get("account");
        String pwd = (String) data.get("password");
        int role = (int) data.get("role");

        Map<String, Object> map = new HashMap<>();

        User user = new User();

        if (role == 1) {
            user = userService.getStudent(name);
        } else if (role == 2){
            user = userService.getEnterprise(name);
        }

       if (user != null && pwd.equals(user.getPwd())) {
            map.put("code", 20001);
        } else if (user == null) {
            map.put("code", 50001);
        } else {
            map.put("code", 50002);
        }
        return map;
    }
}
