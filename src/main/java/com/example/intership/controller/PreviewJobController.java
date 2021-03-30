package com.example.intership.controller;

import com.example.intership.entities.Job;
import com.example.intership.entities.user.Enterprise;
import com.example.intership.service.JobService;
import com.example.intership.service.UserService;
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

    @Autowired
    UserService userService;


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
        data.put("companyAccount", job.getAccount());

        String account = job.getAccount();
        data.put("companyLogoURL", "http://localhost:8089/avatar/2/" + account);

        int jobNum = jobService.getJobNum(account);
        data.put("companyJobNum", jobNum - 1);

        Enterprise enterprise = (Enterprise) userService.getUser(account, 2);
        if (enterprise != null) {
            data.put("companyType", enterprise.getCompanyType());
            data.put("companyAddress", enterprise.getCompanyAddress());
        }

        res.put("data", data);
        res.put("code", 20001);

        return res;
    }

    @ResponseBody
    @GetMapping("/getOtherJob")
    public Map<String, Object> getOtherJob(@RequestParam(value = "account", required = false) String account,
                                           @RequestParam(value = "jobId", required = false) String jobId) {
        ObjectId id = new ObjectId(jobId);
        ArrayList list = new ArrayList();
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> res = new HashMap<>();

        List<Job> jobList = jobService.getOtherJob(account, id);
        for (Job job : jobList) {
            list.add(job.getForm());
        }

        data.put("jobList", list);

        res.put("data", data);
        res.put("code", 20001);

        return res;
    }

    @ResponseBody
    @GetMapping("/CompanyFindJob")
    public Map<String, Object> getPublishJob(@RequestParam(value = "city", required = false) String city,
                                             @RequestParam(value = "type", required = false) String type,
                                             @RequestParam(value = "account", required = false) String account,
                                             @RequestParam(value = "currentPage", required = false) int currentPage,
                                             @RequestParam(value = "pageSize", required = false) int pageSize,
                                             @RequestParam(value = "searchKey", required = false) String searchKey) {
        ArrayList list = new ArrayList();
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> res = new HashMap<>();

        List<Job> jobList = jobService.searchPublishJob(account, city, type, searchKey);
        int realJobListCount = jobList.size() > (pageSize * currentPage) ? pageSize * currentPage : jobList.size();
        for (int i = (currentPage - 1) * pageSize; i < realJobListCount; i++) {
            list.add(jobList.get(i).getFormAddJobDesc());
        }

        data.put("currentJobList", list);
        data.put("totalJobCount", jobList.size());

        res.put("data", data);
        res.put("code", 20001);

        return res;
    }


    @ResponseBody
    @GetMapping("/findJob")
    public Map<String, Object> indJob(@RequestParam(value = "city", required = false) String city,
                                             @RequestParam(value = "type", required = false) String type,
                                             @RequestParam(value = "currentPage", required = false) int currentPage,
                                             @RequestParam(value = "pageSize", required = false) int pageSize,
                                             @RequestParam(value = "searchKey", required = false) String searchKey) {
        ArrayList list = new ArrayList();
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> res = new HashMap<>();

        List<Job> jobList = jobService.findJob(city, type, searchKey);
        int realJobListCount = jobList.size() > (pageSize * currentPage) ? pageSize * currentPage : jobList.size();
        for (int i = (currentPage - 1) * pageSize; i < realJobListCount; i++) {
            list.add(jobList.get(i).getForm());
        }

        data.put("currentJobList", list);
        data.put("totalJobCount", jobList.size());

        res.put("data", data);
        res.put("code", 20001);

        return res;
    }


    @ResponseBody
    @GetMapping("/getHotJobList")
    public Map<String, Object> getHotJobList() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        List<Job> list = jobService.getJobList("全国", "全部");
        ArrayList hotJobList = new ArrayList();
        int i = 0;
        for (Job job : list) {
            i++;
            if (i > 9) {
                break;
            }
            hotJobList.add(job.getForm());
        }
        data.put("hotJobList", hotJobList);
        map.put("data", data);
        map.put("code", 20001);

        return map;

    }


}
