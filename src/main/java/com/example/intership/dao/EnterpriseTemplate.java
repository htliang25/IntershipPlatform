package com.example.intership.dao;

import com.example.intership.entities.job.Job;
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

@Component
public class EnterpriseTemplate {
    @Autowired
    MongoTemplate mongoTemplate;

    /*
        获取全部企业函数
        返回值为企业表中的全部用户
     */
    public List<Enterprise> getEnterpriseList () {
        return mongoTemplate.findAll(Enterprise.class);
    }

    /*
        查找企业函数
        参数为搜索关键词
        返回值为字符匹配的公司
     */
    public List<Enterprise> searchEnterprise (String searchKey) {
        String companyName = ".*?" + searchKey + ".*";
        Criteria criteria1 = Criteria.where("companyName").regex(companyName);
        Query query1 = new Query(criteria1);

        return mongoTemplate.find(query1, Enterprise.class);
    }

    /*
        企业发布岗位函数
        参数为企业帐号account和需要添加的工作
        返回值为状态码
     */
    public int publishJob(String account, ObjectId jobId) {
        Criteria criteria = Criteria.where("account").is(account);
        Query query = new Query(criteria);

        Enterprise enterprise = mongoTemplate.findOne(query, Enterprise.class);

        boolean result = enterprise.JobIsExist(jobId);

        if (result) {
            ArrayList jobList = enterprise.getJobList();
            jobList.add(jobId);
            Update update = new Update();
            update.set("jobList", jobList);
            mongoTemplate.updateMulti(query, update, Enterprise.class);
            return 20001;
        } else {
            return 50001;
        }
    }
}
