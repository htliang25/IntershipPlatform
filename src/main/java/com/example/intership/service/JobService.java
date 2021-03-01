package com.example.intership.service;

import com.example.intership.dao.JobTemplate;
import com.example.intership.dao.UserTemplate;
import com.example.intership.entities.Applicant;
import com.example.intership.entities.Job;
import com.example.intership.entities.user.Enterprise;
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

    @Autowired
    UserTemplate userTemplate;

    public void updateJob(ObjectId id, Map<String, Object> data) {
        jobTemplate.updateJob(id, data);
    }

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

    public int addApplicant(ObjectId id, Applicant applicant) {
        return jobTemplate.addApplicant(id, applicant);
    }

    public List<Job> getOtherJob(String account, ObjectId id) {
        return jobTemplate.getOtherJob(account, id);
    }

    public List<Job> getPublishJob(String account, String city, String type) {
        return jobTemplate.getPublishJob(account, city, type);
    }

    public ArrayList getApplicants(ObjectId id) {
        return jobTemplate.getApplicants(id);
    }

    public List<Job> getUserSearch(String searchKey) {
        List<Job> jobList = jobTemplate.getUserSearch(searchKey);

        return jobTemplate.getUserSearch(searchKey);
    }

    public void deleteJob(ObjectId id) {
        jobTemplate.deleteJob(id);
    }
}
