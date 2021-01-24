package com.example.intership.controller;

import com.example.intership.entities.User;
import com.example.intership.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
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
        User user = userService.getUser(name);

        Map<String, Object> map = new HashMap<String, Object>();
        if (user != null && pwd.equals(user.getPwd())) {
            map.put("ce", "20001");
        } else if (user == null) {
            map.put("code", "50001");
        } else {
            map.put("code", "50002");
        }
        return map;
    }
}
