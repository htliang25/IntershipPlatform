package com.example.intership.dao;

import com.example.intership.entities.Job;
import com.example.intership.entities.User;
import com.example.intership.entities.user.Enterprise;
import com.example.intership.entities.user.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserTemplate {
    @Autowired
    MongoTemplate mongoTemplate;

    public User getUser(String account, int role) {
        Criteria criteria = Criteria.where("account").is(account);
        Query query = new Query(criteria);

        if (role == 1) {
            return mongoTemplate.findOne(query, Student.class);
        } else {
            return mongoTemplate.findOne(query, Enterprise.class);
        }
    }

    public List<Student> getStudentList () {
        return mongoTemplate.findAll(Student.class);
    }

    public List<Enterprise> getEnterpriseList () {
        return mongoTemplate.findAll(Enterprise.class);
    }

    public void saveUser(User user) {
        mongoTemplate.save(user);
    }

    public void modifyPwd(String account, String pwd, String col_name) {
        Criteria criteria = Criteria.where("account").is(account);
        Query query = new Query(criteria);

        Update update = new Update();
        update.set("pwd", pwd);

        mongoTemplate.updateMulti(query, update, col_name);
    }

    public void modifyInfo(String account, String[] str) {
        Criteria criteria = Criteria.where("account").is(account);
        Query query = new Query(criteria);

        Update update = new Update();

        if (str[0].equals("student")) {
            update.set("university", str[1]);
            update.set("major", str[2]);
        } else {
            update.set("companyType", str[1]);
            update.set("companyAddress", str[2]);
            update.set("companyIntro", str[3]);
        }

        mongoTemplate.updateMulti(query, update, str[0]);
    }

    public List<Enterprise> searchEnterprise (String searchKey) {
        String companyName = ".*?" + searchKey + ".*";
        Criteria criteria1 = Criteria.where("companyName").regex(companyName);
        Query query1 = new Query(criteria1);

        return mongoTemplate.find(query1, Enterprise.class);
    }

}
