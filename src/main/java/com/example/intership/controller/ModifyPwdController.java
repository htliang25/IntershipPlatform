package com.example.intership.controller;

import com.example.intership.entities.User;
import com.example.intership.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ModifyPwdController {
    @Autowired
    UserService userService;

    @ResponseBody
    @PostMapping(value = {"/UserModifyPwd", "/EnterPriseModifyPwd"})
    public Map<String, Object> modifyPwd(@RequestBody Map<String, Object> data) {
        String name = (String) data.get("account");
        String old_pwd = (String) data.get("oldPassword");
        String new_pwd = (String) data.get("newPassword");
        int role = (int) data.get("role");
        String col_name = "";
        Map<String, Object> map = new HashMap<>();

        User user = new User();

        if (role == 1) {
            user = userService.getStudent(name);
            col_name = "student";
        } else if (role == 2){
            user = userService.getEnterprise(name);
            col_name = "enterprise";
        }

        if (user != null && old_pwd.equals(user.getPwd())) {
            userService.modifyPwd(name, new_pwd, col_name);
            map.put("code", 20001);
        } else if (user == null) {
            map.put("code", 50001);
        } else {
            map.put("code", 50002);
        }
        return map;
    }
}
