package com.example.intership.controller;

import com.example.intership.entities.Applicant;
import com.example.intership.entities.user.Enterprise;
import com.example.intership.entities.user.Student;
import com.example.intership.service.JobService;
import com.example.intership.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PostResumeController {
    @Autowired
    JobService jobService;

    @Autowired
    UserService userService;

    @ResponseBody
    @PostMapping("/postResume")
    public Map<String, Object> postResume(@RequestBody Map<String, Object> data) {
        String account = (String) data.get("account");
        String entryTime = (String) data.get("entryTime");
        ObjectId _id = new ObjectId((String) data.get("id"));

        Map<String, Object> result = new HashMap<>();

        Applicant applicant = new Applicant();
        applicant.setApplicantAccount(account);
        applicant.setEntryTime(entryTime);
        applicant.setJobId((String) data.get("id"));

        int code = jobService.addApplicant(_id, applicant);
        result.put("code", code);

        return result;
    }

    @ResponseBody
    @GetMapping("/CompanyGetApplicants")
    public Map<String, Object> getApplicants(@RequestParam(value = "account", required = true) String account) {
        ArrayList list = new ArrayList();
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> res = new HashMap<>();

        Enterprise enterprise = (Enterprise) userService.getUser(account, 2);
        ArrayList<Applicant> applicants = enterprise.getApplicants();

        for (Applicant applicant : applicants) {
            // 学生名字 学生学历 投递岗位 入职时间
            String applicantCount = applicant.getApplicantAccount();
            Student student = (Student) userService.getUser(applicantCount, 1);
            HashMap<String, Object> map = new HashMap<>();
            map.put("applicantUniversity", student.getUniversity());
            map.put("applicantMajor", student.getMajor());
            map.put("applicantAccount", applicantCount);
            map.put("entryTime", applicant.getEntryTime());
            map.put("jobId", applicant.getJobId());
            map.put("jobName", applicant.getJobName());
            list.add(map);
        }

        data.put("applicantList", list);
        data.put("applicantCount", list.size());
        res.put("data", data);
        res.put("code", 20001);
        return res;
    }
}