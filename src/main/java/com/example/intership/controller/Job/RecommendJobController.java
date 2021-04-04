package com.example.intership.controller.Job;

import com.example.intership.entities.job.Job;
import com.example.intership.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RecommendJobController {

    @Autowired
    RecommendJobService recommendJobService;

    @Autowired
    JobService jobService;

    @Autowired
    ResumeService resumeService;

    @Autowired
    UserService userService;

    @Autowired
    PictureService pictureService;

    /*
        获取用户推荐工作函数
        api为UserRecommendJob
        参数为用户账号account、现在所在页数currentPage和每页工作数pageSize
        返回值为状态码
     */
    @ResponseBody
    @GetMapping("/UserRecommendJob")
    public Map<String, Object> getJobList(@RequestParam(value = "account", required = false) String account,
                                          @RequestParam(value = "currentPage", required = false) int currentPage,
                                          @RequestParam(value = "pageSize", required = false) int pageSize) {
        ArrayList list = new ArrayList();
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> res = new HashMap<>();

        List<Job> recommendJobList = recommendJobService.getRecommendJobList(account);

        int realJobListCount = recommendJobList.size() > (pageSize * currentPage) ? pageSize * currentPage : recommendJobList.size();
        for (int i = (currentPage - 1) * pageSize; i < realJobListCount; i++) {
            Job job = recommendJobList.get(i);
            list.add(jobService.getForm(job));
        }

        data.put("currentJobList", list);
        data.put("totalJobCount", recommendJobList.size());

        res.put("data", data);
        res.put("code", 20001);

        return res;
    }

}
