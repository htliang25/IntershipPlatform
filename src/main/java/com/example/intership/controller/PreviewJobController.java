package com.example.intership.controller;

import com.example.intership.entities.Job;
import com.example.intership.service.JobService;
import org.bson.types.ObjectId;
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

    @ResponseBody
    @GetMapping("/getJobDetail")
    public Map<String, Object> getJobDetail(@RequestParam(value = "jobId", required = false) String jobId) {
        ObjectId id = new ObjectId(jobId);
        Job job = jobService.getJob(id);

        Map<String, Object> res = new HashMap<>();

        Map<String, Object> data = new HashMap<>();
        data.put("city", job.getJobCity());
        data.put("type", job.getJobType());
        data.put("jobName", job.getJobName());
        data.put("jobDesc", job.getJobDescription());
        data.put("jobDuty", job.getJobDuty());
        data.put("jobRequire", job.getJobRequire());
        data.put("companyName", job.getCompanyName());

        String account = job.getAccount();
        data.put("companyLogoURL", "http://localhost:8089/avatar/2/" + account);

        int jobNum = jobService.getJobNum(account);
        data.put("companyJobNum", jobNum - 1);

        res.put("data", data);
        res.put("code", 20001);

        return res;
    }
}
