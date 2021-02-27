package com.example.intership.controller;

import com.example.intership.service.JobService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ModifyJobController {
    @Autowired
    JobService jobService;

    @ResponseBody
    @PostMapping(value = "CompanyUpdateJob")
    public Map<String, Object> updateJob(@RequestBody Map<String, Object> data) {
        ObjectId id = new ObjectId((String) data.get("jobId"));

        jobService.updateJob(id, data);

        Map<String, Object> res = new HashMap<>();
        res.put("code", 200001);

        return res;
    }

    @ResponseBody
    @PostMapping(value = "CompanyDeleteJob")
    public Map<String, Object> deleteJob(@RequestBody Map<String, Object> data) {
        ObjectId id = new ObjectId((String) data.get("jobId"));

        jobService.deleteJob(id);

        Map<String, Object> res = new HashMap<>();
        res.put("code", 200001);

        return res;
    }
}
