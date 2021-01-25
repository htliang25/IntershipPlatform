package com.example.intership.dao;

import com.example.intership.entities.Enterprise;
import com.example.intership.entities.Student;
import com.example.intership.entities.User;
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

    public Student getStudent(String name) {
        Query query = new Query();
        Criteria criteria = Criteria.where("name").is(name);
        query.addCriteria(criteria).limit(1);
        
        Student student = mongoTemplate.findOne(query, Student.class, "student");
        
        return student;
    }

    public Enterprise getEnterprise(String name) {
        Query query = new Query();
        Criteria criteria = Criteria.where("name").is(name);
        query.addCriteria(criteria).limit(1);

        Enterprise enterprise = mongoTemplate.findOne(query, Enterprise.class, "enterprise");

        return enterprise;
    }

    public void saveStudent(Student student) {
        mongoTemplate.save(student);
    }

    public void saveEnterprise(Enterprise enterprise) {
        mongoTemplate.save(enterprise);
    }
}
