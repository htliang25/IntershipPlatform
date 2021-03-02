package com.example.intership.dao;

import com.example.intership.entities.Applicant;
import com.example.intership.entities.Job;
import com.example.intership.entities.user.Enterprise;
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

        if (!city.equals("全国") && !city.equals("全部城市")) {
            criteria.and("jobCity").is(city);
        }
        if (!type.equals("全部") && !type.equals("全部类型")) {
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

    public List<Job> getPublishJob(String account, String city, String type, String searchKey) {
        Criteria criteria = Criteria.where("account").is(account);

        if (!city.equals("全国") && !city.equals("全部城市")) {
            criteria.and("jobCity").is(city);
        }
        if (!type.equals("全部") && !type.equals("全部类型")) {
            criteria.and("jobType").is(type);
        }
        if (searchKey != null && !searchKey.trim().equals("")) {
            String jobName = ".*?" + searchKey + ".*";
            criteria.and("jobName").regex(jobName);
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

    // 1. job要更新Applicants   2. 企业也需要更新Applicants
    public int addApplicant(ObjectId id, Applicant currentApplicant) {
        Criteria criteria1 = Criteria.where("_id").is(id);
        Query query1 = new Query(criteria1);

        Job job = mongoTemplate.findOne(query1, Job.class);
        currentApplicant.setJobName(job.getJobName());
        ArrayList<Applicant> jobApplicants = job.getApplicants();

        Criteria criteria2 = Criteria.where("account").is(job.getAccount());
        Query query2 = new Query(criteria2);
        Enterprise enterprise = mongoTemplate.findOne(query2, Enterprise.class);
        ArrayList<Applicant> companyApplicants = enterprise.getApplicants();


        String account = currentApplicant.getApplicantAccount();
        boolean result = true;

        if (!jobApplicants.isEmpty()) {
            for(Applicant applicant : jobApplicants) {
                if (applicant.getApplicantAccount().equals(currentApplicant.getApplicantAccount())) {
                    result = false;
                    break;
                }
            }
        }

        if (result) {
            Update update1 = new Update();
            jobApplicants.add(currentApplicant);
            update1.set("applicants", jobApplicants);
            mongoTemplate.updateMulti(query1, update1, "job");

            Update update2 = new Update();
            companyApplicants.add(currentApplicant);
            update2.set("applicants", companyApplicants);
            mongoTemplate.updateMulti(query2, update2, "enterprise");

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
        String jobName = ".*?" + searchKey + ".*";
        Criteria criteria1 = Criteria.where("jobName").regex(jobName);
        Query query1 = new Query(criteria1);

        List<Job> jobList = mongoTemplate.find(query1, Job.class);

        String companyName = ".*?" + searchKey + ".*";
        Criteria criteria2 = Criteria.where("companyName").regex(companyName);
        Query query2 = new Query(criteria2);

        Enterprise enterprise = mongoTemplate.findOne(query2, Enterprise.class);
        if (enterprise != null) {
            List<Job> companyJobList = getPublishJob(enterprise.getAccount(), "全国", "全部", "");
            companyJobList.addAll(jobList);
            return companyJobList;
        }

        return jobList;
    }

    public void updateJob(ObjectId id, Map<String, Object> data) {
        Criteria criteria = Criteria.where("_id").is(id);
        Query query = new Query(criteria);
        Update update = Job.modifyJob(data);

        mongoTemplate.updateMulti(query, update, "job");
    }

    public void deleteJob(ObjectId id) {
        Criteria criteria = Criteria.where("_id").is(id);
        Query query = new Query(criteria);

        mongoTemplate.remove(query, Job.class);
    }
}
