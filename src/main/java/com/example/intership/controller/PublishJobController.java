package com.example.intership.controller;

import com.example.intership.entities.Job;
import com.example.intership.entities.user.Enterprise;
import com.example.intership.service.JobService;
import com.example.intership.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class PublishJobController {
    @Autowired
    JobService jobService;

    @Autowired
    UserService userService;

    @ResponseBody
    @PostMapping("/CompanyPublishJob")
    public Map<String, Object> publishJob(@RequestBody Map<String, Object> data) {
        String account = (String) data.get("account");

        Enterprise enterprise = (Enterprise) userService.getUser(account, 2);
        String companyName = enterprise.getCompanyName();

        Job job = new Job(account, companyName);
        job.setAttributes(data);
        jobService.publishJob(job);

        Map<String, Object> res = new HashMap<>();
        res.put("code", 20001);

        return res;
    }
}
