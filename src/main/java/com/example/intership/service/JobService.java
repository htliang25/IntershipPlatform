package com.example.intership.service;

import com.example.intership.dao.JobTemplate;
import com.example.intership.entities.Job;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
