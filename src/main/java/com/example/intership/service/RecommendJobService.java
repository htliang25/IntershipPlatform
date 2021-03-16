package com.example.intership.service;

import com.example.intership.dao.RecommendJobTemplate;
import com.example.intership.entities.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendJobService {

    @Autowired
    RecommendJobTemplate recommendJobTemplate;

    public List<Job> getRecommendJobList (String account) {
        return recommendJobTemplate.getRecommendJobList(account);
    }

    public void updateRecommendJobList (String account, ArrayList<Job> recommendJobList) {
        recommendJobTemplate.updateRecommendList(account, recommendJobList);
    }
}
