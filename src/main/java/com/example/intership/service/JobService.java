package com.example.intership.service;

import com.example.intership.dao.JobTemplate;
import com.example.intership.entities.Job;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class JobService {
    @Autowired
    JobTemplate jobTemplate;

    public void publishJob(Job job) {
        jobTemplate.publishJob(job);
    }

    public List<Job> getJobList(String city, String type) {
        return jobTemplate.getJobList(city, type);
    }

    public Job getJob(ObjectId id) {
        return jobTemplate.getJob(id);
    }

    public int getJobNum(String account) {
        return jobTemplate.getJobNum(account);
    }

    public int addApplicant(ObjectId id, Map<String, Object> applicant) {
        return jobTemplate.addApplicant(id, applicant);
    }

    public List<Job> getOtherJob(String account, ObjectId id) {
        return jobTemplate.getOtherJob(account, id);
    }

    public List<Job> getPublishJob(String account) {
        return jobTemplate.getPublishJob(account);
    }

    public ArrayList getApplicants(ObjectId id) {
        return jobTemplate.getApplicants(id);
    }

    public List<Job> getUserSearch(String searchKey) {
        return jobTemplate.getUserSearch(searchKey);
    }
}
