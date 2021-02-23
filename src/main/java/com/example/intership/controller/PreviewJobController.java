package com.example.intership.controller;

import com.example.intership.entities.Job;
import com.example.intership.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PreviewJobController {
    @Autowired
    JobService jobService;

    @ResponseBody
    @GetMapping("/getJobList")
    public Map<String, Object> getJobList(@RequestParam(value = "city", required = false) String city,
                                          @RequestParam(value = "type", required = false) String type) {
        ArrayList list = new ArrayList();
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> res = new HashMap<>();

        List<Job> jobList = jobService.getJobList(city, type);
        for (Job job : jobList) {
            list.add(job.getForm());
        }

        data.put("jobList", list);

        res.put("data", data);
        res.put("code", 20001);

        return res;
    }
}
