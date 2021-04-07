package com.example.intership.dao;

import com.example.intership.entities.job.Applicant;
import com.example.intership.entities.job.Job;
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

    @Autowired
    PictureTemplate pictureTemplate;

    /*
        发布工作函数
        参数为工作job
     */
    public void publishJob(Job job) {
        mongoTemplate.save(job);
    }

    /*
        获取工作列表函数
        参数为工作所在城市city和工作类型type
        返回值为符合条件的工作列表
     */
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

    /*
        获取企业其他工作函数
        参数为企业帐号account和被排除工作id
        返回值为符合条件的工作列表
     */
    public List<Job> getOtherJob(String account, ObjectId id) {
        Criteria c = Criteria.where("_id").is(id);
        Criteria criteria = Criteria.where("account").is(account).norOperator(c);
        Query query = new Query(criteria);

        return mongoTemplate.find(query, Job.class);
    }

    /*
        搜索企业发布工作函数
        参数为企业帐号account、工作所在城市city和工作类型type和搜索关键字searchKey
        返回值为符合条件的工作列表
     */
    public List<Job> searchPublishJob(String account, String city, String type, String searchKey) {
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

    /*
        搜索工作函数
        参数为工作所在城市city和工作类型type和搜索关键字searchKey
        返回值为符合条件的工作列表
     */
    public List<Job> findJob(String city, String type, String searchKey) {
        Criteria criteria = new Criteria();

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

    /*
        获取工作函数
        参数为工作id
        返回值为指定的工作
     */
    public Job getJob(ObjectId id) {
        Criteria criteria = Criteria.where("_id").is(id);
        Query query = new Query(criteria);

        return mongoTemplate.findOne(query, Job.class);
    }

    /*
        获取指定企业发布工作数函数
        参数为企业帐号account
        返回值为指定企业发布工作数
     */
    public int getJobNum(String account) {
        Criteria criteria = Criteria.where("account").is(account);
        Query query = new Query(criteria);

        return (int) mongoTemplate.count(query, Job.class);
    }

    /*
        添加岗位申请者函数
        参数为工作id和申请者applicant
        返回值为是否投递成功的布尔值
     */
    public boolean addApplicant(ObjectId id, Applicant currentApplicant) {
        Criteria criteria = Criteria.where("_id").is(id);
        Query query = new Query(criteria);

        Job job = mongoTemplate.findOne(query, Job.class);
        boolean flag = job.applicantIsExist(currentApplicant);

        if (!flag) {
            ArrayList applicants = job.getApplicants();
            int applicantCount = job.getApplicantCount();
            applicants.add(currentApplicant);
            Update update = new Update();
            update.set("applicants", applicants);
            update.set("applicantCount", ++applicantCount);
            mongoTemplate.updateMulti(query, update, Job.class);
            return true;
        } else {
            return false;
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

        return mongoTemplate.find(query1, Job.class);
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

    public Map<String, Object> getForm(Job job) {
        Map<String, Object> data = job.getForm();
        boolean flag = pictureTemplate.avatarIsExist(job.getAccount(), 2);
        if (flag) {
            ObjectId avatarId = pictureTemplate.getAvatarId(job.getAccount(), 2);
            data.put("logoURL", "/api/avatar/" + avatarId);
        }
        return data;
    }

    public Map<String, Object> getFormAddJobDesc(Job job) {
        Map<String, Object> data = job.getFormAddJobDesc();
        boolean flag = pictureTemplate.avatarIsExist(job.getAccount(), 2);
        if (flag) {
            ObjectId avatarId = pictureTemplate.getAvatarId(job.getAccount(), 2);
            data.put("logoURL", "/api/avatar/" + avatarId);
        }
        return data;
    }
}
