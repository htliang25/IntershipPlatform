package com.example.intership.controller;

import com.example.intership.entities.user.Enterprise;
import com.example.intership.entities.user.Student;
import com.example.intership.service.JobService;
import com.example.intership.service.ResumeService;
import com.example.intership.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.*;

@Controller
public class PreviewUserController {
    @Autowired
    UserService userService;

    @Autowired
    JobService jobService;

    @Autowired
    ResumeService resumeService;

    @ResponseBody
    @GetMapping(value = {"/getUserInfo", "/getEnterpriseInfo"})
    public Map<String, Object> getUser(@RequestParam(value = "account", required = false) String account,
                                       @RequestParam(value = "role", required = false) int role) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        if (role == 1) {
            Student student = (Student) userService.getUser(account, role);

            if (student != null) {
                data.put("university", student.getUniversity());
                data.put("major", student.getMajor());

                Map<String, Object> infoForm = resumeService.getSingleForm(account, "informationForm");
                data.put("infoForm", infoForm);

                data.put("avatarURL", "http://localhost:8089/avatar/1/" + account + '/' + new Date().getTime());
                map.put("code", 20001);
                map.put("data", data);
            } else {
                //用户不存在
                map.put("code", 50001);
            }
        } else {
            Enterprise enterprise = (Enterprise) userService.getUser(account, role);

            if (enterprise != null) {
                data.put("companyName", enterprise.getCompanyName());
                data.put("companyIntro", enterprise.getCompanyIntro());
                data.put("companyLogoURL", "http://localhost:8089/avatar/2/" + account + '/' + new Date().getTime());
                data.put("companyType", enterprise.getCompanyType());
                data.put("companyAddress", enterprise.getCompanyAddress());
                data.put("companyJobCount", jobService.getJobNum(account));
                map.put("code", 20001);
                map.put("data", data);
            } else {
                //用户不存在
                map.put("code", 50001);
            }
        }

        return map;
    }


    @ResponseBody
    @GetMapping("/getHotCompanyList")
    public Map<String, Object> getHotCompanyList() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        List<Enterprise> list = userService.getEnterpriseList();
        ArrayList hotCompanyList = new ArrayList();
        int i = 0;
        for (Enterprise enterprise : list) {
            i++;
            if (i > 12) {
                break;
            }
            HashMap<String, Object> enterpriseMsg = new HashMap<>();
            enterpriseMsg.put("logoURL", "http://localhost:8089/avatar/2/" + enterprise.getAccount() + '/' + new Date().getTime());
            enterpriseMsg.put("companyName", enterprise.getCompanyName());
            enterpriseMsg.put("companyAccount", enterprise.getAccount());
            hotCompanyList.add(enterpriseMsg);
        }
        data.put("hotCompanyList", hotCompanyList);
        map.put("data", data);
        map.put("code", 20001);

        return map;
    }

}
