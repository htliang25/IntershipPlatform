package com.example.intership.controller;

import com.example.intership.entities.User;
import com.example.intership.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam("username")String username,
                        @RequestParam("password")String password,
                        @RequestParam("role")String role,
                        HttpSession session) {
        User user = userService.getUser(username);
        if (user != null && password.equals(user.getPwd())) {
            //登录成功
            session.setAttribute("loginUser", username);
            return "redirect:/main.html";
        } else if (user == null) {
            //用户不存在
            return "login";
        } else {
            //密码错误
            return "login";
        }
    }
}
