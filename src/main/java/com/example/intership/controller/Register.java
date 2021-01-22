package com.example.intership.controller;

import com.example.intership.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Register {
    @Autowired
    UserService userService;

    @RequestMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam int role) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            //用户名或密码为空
            return "register";
        } else if (userService.getUser(username) != null) {
            //用户名已被注册
            return "register";
        } else {
            //注册成功
            userService.saveUser(username, password, role);
            return "login";
        }
    }
}
