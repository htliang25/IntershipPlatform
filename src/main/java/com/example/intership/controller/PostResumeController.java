package com.example.intership.controller;

import com.example.intership.service.JobService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class PostResumeController {
    @Autowired
    JobService jobService;

    @ResponseBody
    @PostMapping("/postResume")
    public void postResume(@RequestBody Map<String, Object> data) {
        String account = (String) data.get("account");
        String inTime = (String) data.get("inTime");
        ObjectId _id = new ObjectId((String) data.get("id"));

        Map<String, Object> applicant = new HashMap<>();
        applicant.put("applicantAccount", account);
        applicant.put("inTime", inTime);

        jobService.addApplicant(_id, applicant);
    }
}
