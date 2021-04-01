package com.example.intership.controller;

import com.example.intership.entities.Job;
import com.example.intership.entities.user.Enterprise;
import com.example.intership.service.JobService;
import com.example.intership.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class SearchController {

    @Autowired
    JobService jobService;

    @Autowired
    UserService userService;

    // 加入分页功能
    @ResponseBody
    @GetMapping("/SearchAnything")
    public Map<String, Object> getUserSearch(@RequestParam(value = "searchKey", required = false) String searchKey,
                                             @RequestParam(value = "currentPage", required = false) int currentPage,
                                             @RequestParam(value = "pageSize", required = false) int pageSize ) {

        Map<String, Object> data = new HashMap<>();
        Map<String, Object> res = new HashMap<>();

        List<Job> jobList = jobService.getUserSearch(searchKey);

        ArrayList finalJobList = new ArrayList();

        int realJobListCount = jobList.size() > (pageSize * currentPage) ? pageSize * currentPage : jobList.size();
        for (int i = (currentPage - 1) * pageSize; i < realJobListCount; i++) {
            finalJobList.add(jobList.get(i).getForm());
        }


        List<Enterprise> enterpriseList = userService.searchEnterprise(searchKey);

        ArrayList finalEnterpriseList = new ArrayList();

        for (Enterprise enterprise : enterpriseList) {
            HashMap<String, Object> enterpriseMsg = new HashMap<>();
            enterpriseMsg.put("companyName", enterprise.getCompanyName());
            enterpriseMsg.put("companyIntro", enterprise.getCompanyIntro());
            enterpriseMsg.put("companyLogoURL", "http://localhost:8089/avatar/2/" + enterprise.getAccount() + '/' + new Date().getTime());
            enterpriseMsg.put("companyAccount", enterprise.getAccount());

            finalEnterpriseList.add(enterpriseMsg);
        }


        data.put("companyList", finalEnterpriseList);
        data.put("currentJobList", finalJobList);
        data.put("totalJobCount", jobList.size());
        res.put("data", data);
        res.put("code", 20001);

        return res;
    }

}
