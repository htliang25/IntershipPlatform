package com.example.intership.dao;

import com.example.intership.entities.Job;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public List<Job> getOtherJob(String account, ObjectId id) {
        Criteria c = Criteria.where("_id").is(id);
        Criteria criteria = Criteria.where("account").is(account).norOperator(c);
        Query query = new Query(criteria);

        return mongoTemplate.find(query, Job.class);
    }

    public List<Job> getPublishJob(String account, String city, String type) {
        Criteria criteria = Criteria.where("account").is(account);

        if (!city.equals("全国")) {
            criteria.and("jobCity").is(city);
        }
        if (!type.equals("全部")) {
            criteria.and("jobType").is(type);
        }

        Query query = new Query(criteria);

        return mongoTemplate.find(query, Job.class);
    }

    public Job getJob(ObjectId id) {
        Criteria criteria = Criteria.where("_id").is(id);
        Query query = new Query(criteria);

        return mongoTemplate.findOne(query, Job.class);
    }

    public int getJobNum(String account) {
        Criteria criteria = Criteria.where("account").is(account);
        Query query = new Query(criteria);

        return (int) mongoTemplate.count(query, Job.class);
    }

    public int addApplicant(ObjectId id, Map<String, Object> applicant) {
        Criteria criteria = Criteria.where("_id").is(id);
        Query query = new Query(criteria);

        Job job = mongoTemplate.findOne(query, Job.class);
        ArrayList applicants = job.getApplicants();

        String account = (String) applicant.get("applicantAccount");
        boolean result = true;

        if (!applicants.isEmpty()) {
            for(Object obj : applicants) {
                Map<String, Object> user = (Map<String, Object>) obj;

                if (user.containsValue(account)) {
                    result = false;
                    break;
                }
            }
        }

        if (result) {
            Update update = new Update();
            update.set("applicants", applicants);

            applicants.add(applicant);
            mongoTemplate.updateMulti(query, update, "job");

            return 20001;
        } else {
            return 50001;
        }
    }

    public ArrayList getApplicants(ObjectId id) {
        Criteria criteria = Criteria.where("_id").is(id);
        Query query = new Query(criteria);

        Job job = mongoTemplate.findOne(query, Job.class);

        return job.getApplicants();
    }

    public List<Job> getUserSearch(String searchKey) {
        String jobName = ".*?" + searchKey + ".*?";
        Criteria criteria = Criteria.where("jobName").regex(jobName);
        Query query = new Query(criteria);

        return mongoTemplate.find(query, Job.class);
    }
}
