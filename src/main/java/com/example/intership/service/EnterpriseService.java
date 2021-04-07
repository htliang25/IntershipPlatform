package com.example.intership.service;

import com.example.intership.dao.EnterpriseTemplate;
import com.example.intership.entities.user.Enterprise;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnterpriseService {
    @Autowired
    EnterpriseTemplate enterpriseTemplate;

    public List<Enterprise> searchEnterprise (String searchKey) {
        return enterpriseTemplate.searchEnterprise(searchKey);
    }

    public List<Enterprise> getEnterpriseList () {
        return enterpriseTemplate.getEnterpriseList();
    }

    public int publishJob(String account, ObjectId jobId) {
        return enterpriseTemplate.publishJob(account, jobId);
    }

    public void deleteJob(ObjectId jobId) {
        enterpriseTemplate.deleteJob(jobId);
    }
}
