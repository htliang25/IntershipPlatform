package com.example.intership.dao;

import com.example.intership.entities.job.Applicant;
import com.example.intership.entities.job.Job;
import com.example.intership.entities.user.Student;
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
public class StudentTemplate {
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    UserTemplate userTemplate;

    @Autowired
    JobTemplate jobTemplate;

    /*
        获取全部学生函数
        返回值为学生表中的全部用户
     */
    public List<Student> getStudentList () {
        return mongoTemplate.findAll(Student.class);
    }

    /*
        学生增加投递岗位函数
        参数为学生帐号和投递岗位jobId
        返回值为是否投递成功的布尔值
     */
    public boolean addJob(String account, ObjectId jobId) {
        Criteria criteria = Criteria.where("account").is(account);
        Query query = new Query(criteria);

        Student student = (Student) userTemplate.getUser(account, 1);
        boolean flag = student.JobIsExist(jobId);

        if (!flag) {
            ArrayList jobList = student.getJobList();
            jobList.add(jobId);
            Update update = new Update();
            update.set("jobList", jobList);
            mongoTemplate.updateMulti(query, update, Student.class);
            return true;
        } else {
            return false;
        }
    }

    public void deleteJob(ObjectId jobId) {
        Job job = jobTemplate.getJob(jobId);
        ArrayList<Applicant> applicants = job.getApplicants();

        for (Applicant applicant : applicants) {
            String account = applicant.getApplicantAccount();
            Student student = (Student) userTemplate.getUser(account, 1);
            ArrayList jobList = student.getJobList();
            jobList.remove(jobId);

            Criteria criteria = Criteria.where("account").is(account);
            Query query = new Query(criteria);
            Update update = new Update();
            update.set("jobList", jobList);
            mongoTemplate.updateMulti(query, update, Student.class);
        }
    }
}
