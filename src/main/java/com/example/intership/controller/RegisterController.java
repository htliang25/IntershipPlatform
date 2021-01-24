package com.example.intership.controller;

import com.example.intership.entities.User;
import com.example.intership.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class RegisterController {
    @Autowired
    UserService userService;

    @RequestMapping("/register")
    public String register(@RequestBody Map<String, Object> data) {
        String name = (String) data.get("account");
        if (userService.getUser(name) == null) {
            User user = new User();
            user.setName((String) data.get("account"));
            user.setPwd((String) data.get("password"));
            user.setUniversity((String) data.get("university"));
            user.setMajor((String) data.get("major"));
            userService.saveUser(user);
        } else {

        }
        return "";
    }
}
