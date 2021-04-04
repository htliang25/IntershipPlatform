package com.example.intership.controller;

import com.example.intership.entities.job.Job;
import com.example.intership.entities.user.Enterprise;
import com.example.intership.service.JobService;
import com.example.intership.service.PictureService;
import com.example.intership.service.UserService;
import org.bson.types.ObjectId;
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

    @Autowired
    PictureService pictureService;

    /*
        岗位搜索函数
        api为SearchAnything
        参数为搜索关键字searchKey、现在所在页数currentPage和每页工作数pageSize
        返回值为工作列表和状态码
     */
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
            Job job = jobList.get(i);
            finalJobList.add(jobService.getForm(job));
        }

        List<Enterprise> enterpriseList = userService.searchEnterprise(searchKey);

        ArrayList finalEnterpriseList = new ArrayList();

        for (Enterprise enterprise : enterpriseList) {
            HashMap<String, Object> enterpriseMsg = new HashMap<>();
            ObjectId avatarId = pictureService.getAvatarId(enterprise.getAccount(), 2);

            enterpriseMsg.put("companyName", enterprise.getCompanyName());
            enterpriseMsg.put("companyIntro", enterprise.getCompanyIntro());
            enterpriseMsg.put("companyLogoURL", "http://localhost:8089/avatar/" + avatarId);
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
