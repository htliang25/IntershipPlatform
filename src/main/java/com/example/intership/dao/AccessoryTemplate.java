package com.example.intership.dao;

import com.example.intership.entities.resume.Accessory;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccessoryTemplate {
    //只对简历附件作用
    @Autowired
    MongoTemplate mongoTemplate;

    // 通过url 来访问某个附件地址
    public Accessory getAccessoryDetail (String account, String fileName) {
        Criteria criteria = Criteria.where("account").is(account).and("name").is(fileName);
        Query query = new Query(criteria);

        return mongoTemplate.findOne(query, Accessory.class);
    }


    // 通过附件id 来删除某个附件
    public void removeResumeAccessory (ObjectId accessoryId) {
        Criteria criteria = Criteria.where("_id").is(accessoryId);
        Query query = new Query(criteria);

        mongoTemplate.remove(query, Accessory.class);
    }

    public List<Accessory> getAccessory(String account) {
        Criteria criteria = Criteria.where("account").is(account);
        Query query = new Query(criteria);

        return mongoTemplate.find(query, Accessory.class);
    }

    public void saveAccessory(Accessory accessory) {
        mongoTemplate.save(accessory);
    }

    public void deleteAccessory(String account, String name) {
        Criteria criteria = Criteria.where("account").is(account).and("name").is(name);
        Query query = new Query(criteria);

        mongoTemplate.remove(query, Accessory.class);
    }

    public boolean isExist(String account, String name) {
        Criteria criteria = Criteria.where("account").is(account).and("name").is(name);
        Query query = new Query(criteria);

        Accessory accessory = mongoTemplate.findOne(query, Accessory.class);
        return (accessory != null) ? true : false;
    }
}
