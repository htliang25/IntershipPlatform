package com.example.intership.dao;

import com.example.intership.entities.message.Message;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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

    public void readMessage(ObjectId id) {
        Criteria criteria = Criteria.where("_id").is(id);
        Query query = new Query(criteria);

        Message message = mongoTemplate.findOne(query, Message.class);
        if (message != null) {
            if (!message.isRead()) {
                Update update = new Update();
                update.set("read", true);
                mongoTemplate.updateMulti(query, update, "message");
            }
        }


    }

}
