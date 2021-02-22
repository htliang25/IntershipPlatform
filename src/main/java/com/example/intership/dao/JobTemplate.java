package com.example.intership.dao;

import com.example.intership.entities.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobTemplate {
    @Autowired
    MongoTemplate mongoTemplate;

    public void publishJob(Job job) {
        mongoTemplate.save(job);
    }

    public List<Job> getJobList(String city, String type) {
        Criteria criteria = new Criteria();

        if (!city.equals("全国")) {
            criteria.and("jobCity").is(city);
        }
        if (!type.equals("全部")) {
            criteria.and("jobType").is(type);
        }

        Query query = new Query(criteria);

        return mongoTemplate.find(query, Job.class);
    }
}
