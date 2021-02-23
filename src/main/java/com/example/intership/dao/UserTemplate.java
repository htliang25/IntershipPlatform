package com.example.intership.dao;

import com.example.intership.entities.User;
import com.example.intership.entities.user.Enterprise;
import com.example.intership.entities.user.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

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

        if (str[2].equals("student")) {
            update.set("university", str[0]);
            update.set("major", str[1]);
        } else {
            update.set("companyIntro", str[1]);
        }

        mongoTemplate.updateMulti(query, update, str[2]);
    }
}
