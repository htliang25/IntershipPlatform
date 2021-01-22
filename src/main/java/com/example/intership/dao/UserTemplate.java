package com.example.intership.dao;

import com.example.intership.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserTemplate {
    @Autowired
    MongoTemplate mongoTemplate;

    //获取全部用户
    public List<User> getUsers() {
        return mongoTemplate.findAll(User.class);
    }

    //获取指定用户
    public User getuser(String name) {
        Query query = new Query();
        Criteria criteria = Criteria.where("name").is(name);
        query.addCriteria(criteria).limit(1);
        User user = mongoTemplate.findOne(query, User.class, "user");
        return user;
    }

    public void saveUser(User user){
        mongoTemplate.save(user);
    }

}
