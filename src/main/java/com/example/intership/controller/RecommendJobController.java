package com.example.intership.controller;

import com.example.intership.entities.Job;
import com.example.intership.service.RecommendJobService;
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
public class RecommendJobController {

    @Autowired
    RecommendJobService recommendJobService;

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
            list.add(recommendJobList.get(i).getForm());
        }

        data.put("currentJobList", list);
        data.put("totalJobCount", recommendJobList.size());

        res.put("data", data);
        res.put("code", 20001);

        return res;
    }
}
