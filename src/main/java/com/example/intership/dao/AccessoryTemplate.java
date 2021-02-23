package com.example.intership.dao;

import com.example.intership.entities.Accessory;
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

    public ArrayList getAccessory(String account) {
        Criteria criteria = Criteria.where("account").is(account);
        Query query = new Query(criteria);

        ArrayList data = new ArrayList();

        List<Accessory> accessories = mongoTemplate.find(query, Accessory.class, "accessory");
        for (Accessory accessory : accessories) {
            data.add(accessory.getFile());
        }

        return data;
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

    public void deleteAccessory(String account, int role, String colName) {
        Criteria criteria = Criteria.where("account").is(account).and("role").is(role);
        Query query = new Query(criteria);

        mongoTemplate.remove(query, Accessory.class, colName);
    }

    public boolean isExist(String account, int role, String colName) {
        Criteria criteria = Criteria.where("account").is(account).and("role").is(role);
        Query query = new Query(criteria);

        Accessory accessory = mongoTemplate.findOne(query, Accessory.class, colName);
        return (accessory != null) ? true : false;
    }
}
