package com.example.intership.controller;

import com.example.intership.entities.Enterprise;
import com.example.intership.entities.Student;
import com.example.intership.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShowController {
    @Autowired
    UserService userService;

    @ResponseBody
    @GetMapping(value = {"/getUserInfo", "/getEnterpriseInfo"})
    public Map<String, Object> getUser(@RequestBody Map<String, Object> data) {
        String name = (String) data.get("account");
        int role = (int) data.get("role");

        Map map = new HashMap<String, Object>();

        if (role == 1) {
            Student student = userService.getStudent(name);
            if (student != null) {
                data.put("university", student.getUniversity());
                data.put("major", student.getMajor());
                map.put("code", 20001);
            } else {
                //用户不存在
                map.put("code", 50001);
            }
        } else {
            Enterprise enterprise = userService.getEnterprise(name);
            if (enterprise != null) {
                data.put("companyName", enterprise.getCompanyName());
                data.put("companyIntro", enterprise.getCompanyIntro());
                map.put("code", 20001);
            } else {
                //用户不存在
                map.put("code", 50001);
            }
        }

        return map;
    }

    @ResponseBody
    @GetMapping("/students")
    public List<Student> getStudents() {
        return userService.getStudents();
    }

    @ResponseBody
    @GetMapping("/enterprises")
    public List<Enterprise> getEnterprises() {
        return userService.getEnterprises();
    }
}
