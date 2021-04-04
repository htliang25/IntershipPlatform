package com.example.intership.dao;

import com.example.intership.entities.form.Accessory;
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
    /*
        只对简历附件作用
     */
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

    public List<Accessory> getAccessory(String account) {
        Criteria criteria = Criteria.where("account").is(account);
        Query query = new Query(criteria);

        ArrayList data = new ArrayList();

        return mongoTemplate.find(query, Accessory.class, "accessory");
    }

    public void saveAccessory(Accessory accessory) {
        mongoTemplate.save(accessory);
    }

    public void deleteAccessory(String account) {
        Criteria criteria = Criteria.where("account").is(account);
        Query query = new Query(criteria);

        mongoTemplate.remove(query, Accessory.class);
    }

    public boolean isExist(String account) {
        Criteria criteria = Criteria.where("account").is(account);
        Query query = new Query(criteria);

        Accessory accessory = mongoTemplate.findOne(query, Accessory.class);
        return (accessory != null) ? true : false;
    }
}
