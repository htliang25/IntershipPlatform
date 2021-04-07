package com.example.intership.dao;

import com.example.intership.entities.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    public void saveMessage(Message message) {
        mongoTemplate.save(message);
    }

    public Message getMessage(String jobId, String applicantAccount) {
        Criteria criteria = Criteria.where("jobId").is(jobId).and("applicantAccount").is(applicantAccount);
        Query query = new Query(criteria);

        return mongoTemplate.findOne(query, Message.class);
    }

    public List<Message> getMyNotifyMessageList(String applicantAccount) {
        Criteria criteria = Criteria.where("applicantAccount").is(applicantAccount);
        Query query = new Query(criteria);

        return mongoTemplate.find(query, Message.class);
    }

}
