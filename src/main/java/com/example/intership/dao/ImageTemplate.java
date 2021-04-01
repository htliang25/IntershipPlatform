package com.example.intership.dao;

import com.example.intership.entities.Accessory;
import com.example.intership.entities.Image;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class ImageTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    public void saveImage(Image image) {
        mongoTemplate.save(image);
    }

    public Image getImage(ObjectId imageId) {
        Criteria criteria = Criteria.where("_id").is(imageId);
        Query query = new Query(criteria);

        return mongoTemplate.findOne(query, Image.class);
    }

    public void delImage(ObjectId imageId) {
        Criteria criteria = Criteria.where("_id").is(imageId);
        Query query = new Query(criteria);

        mongoTemplate.remove(query, Image.class);
    }

}
