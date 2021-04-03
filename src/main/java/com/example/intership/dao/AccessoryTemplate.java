package com.example.intership.dao;

import com.example.intership.entities.Accessory;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccessoryTemplate {
    @Autowired
    MongoTemplate mongoTemplate;


    // 通过url 来访问某个附件地址
    public Accessory getAccessoryDetail (String account, String fileName) {
        Criteria criteria = Criteria.where("account").is(account).and("name").is(fileName);
        Query query = new Query(criteria);

        return mongoTemplate.findOne(query, Accessory.class, "accessory");
    }


    // 通过附件id 来删除某个附件
    public void removeResumeAccessory (ObjectId accessoryId) {
        Criteria criteria = Criteria.where("_id").is(accessoryId);
        Query query = new Query(criteria);

        mongoTemplate.remove(query, Accessory.class, "accessory");
    }

    public List<Accessory> getAccessory(String account, int role) {
        Criteria criteria = Criteria.where("account").is(account).and("role").is(role);
        Query query = new Query(criteria);

        ArrayList data = new ArrayList();

        return mongoTemplate.find(query, Accessory.class, "accessory");
    }

    public Accessory getAvatar(String account, int role) {
        Criteria criteria = Criteria.where("account").is(account).and("role").is(role);
        Query query = new Query(criteria);

        Accessory accessory = mongoTemplate.findOne(query, Accessory.class, "avatar");

        return accessory;
    }

    public void saveAccessory(Accessory accessory, String colName) {
        mongoTemplate.save(accessory, colName);
    }

    public void deleteAccessory(String account, int role, String fileName, String colName) {
        Criteria criteria = Criteria.where("account").is(account).and("role").is(role).and("name").is(fileName);
        Query query = new Query(criteria);

        mongoTemplate.remove(query, Accessory.class, colName);
    }

    public boolean isExist(String account, int role, String fileName, String colName) {
        Criteria criteria = Criteria.where("account").is(account).and("role").is(role).and("name").is(fileName);
        Query query = new Query(criteria);

        Accessory accessory = mongoTemplate.findOne(query, Accessory.class, colName);
        return (accessory != null) ? true : false;
    }

    public void deleteAvatar(String account, int role, String colName) {
        Criteria criteria = Criteria.where("account").is(account).and("role").is(role);
        Query query = new Query(criteria);

        mongoTemplate.remove(query, Accessory.class, colName);
    }

    public boolean isAvatarExist(String account, int role, String colName) {
        Criteria criteria = Criteria.where("account").is(account).and("role").is(role);
        Query query = new Query(criteria);

        Accessory accessory = mongoTemplate.findOne(query, Accessory.class, colName);
        return (accessory != null) ? true : false;
    }

}
