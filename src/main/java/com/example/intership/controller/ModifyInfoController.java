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
public class ModifyInfoController {
    @Autowired
    UserService userService;

    @ResponseBody
    @PostMapping(value = {"/UserModifyInfo", "/EnterPriseModifyInfo"})
    public Map<String, Object> modifyInfo(@RequestBody Map<String, Object> data) {
        String account = (String) data.get("account");
        int role = (int) data.get("role");
        String[] str = new String[3];

        Map map = new HashMap<String, Object>();

        User user = userService.getUser(account, role);

        if (role == 1) {
            str[0] = (String) data.get("university");
            str[1] = (String) data.get("major");
            str[2] = "student";
        } else {
            str[0] = "";
            str[1] = (String) data.get("companyIntro");
            str[2] = "enterprise";
        }

        if (user != null) {
            userService.modifyInfo(account, str);
            map.put("code", 20001);
        } else {
            map.put("code", 50001);
        }

        return map;
    }
}
