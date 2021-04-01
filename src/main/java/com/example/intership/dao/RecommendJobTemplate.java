package com.example.intership.dao;

import com.example.intership.entities.Job;
import com.example.intership.entities.RecommendJob;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecommendJobTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Job> getRecommendJobList (String account) {
        RecommendJob recommendJob = getRecommendJob(account);
        if (recommendJob != null) {
            return recommendJob.getRecommendJobList();
        } else {
            return new ArrayList<>();
        }
    }

    public RecommendJob getRecommendJob (String account) {
        Criteria criteria = Criteria.where("account").is(account);
        Query query = new Query(criteria);
        return mongoTemplate.findOne(query, RecommendJob.class);
    }

    public void updateRecommendList (String account, ArrayList<Job> recommendJobList) {
        RecommendJob recommendJob = getRecommendJob(account);
        if (recommendJob == null) {
            recommendJob = new RecommendJob();
            recommendJob.setRecommendJobList(recommendJobList);
            recommendJob.setAccount(account);
            mongoTemplate.save(recommendJob);
        } else {
            Update update = new Update();
            update.set("recommendJobList", recommendJobList);

            Criteria criteria = Criteria.where("account").is(account);
            Query query = new Query(criteria);
            mongoTemplate.updateMulti(query, update, "recommendJob");
        }
    }


}
