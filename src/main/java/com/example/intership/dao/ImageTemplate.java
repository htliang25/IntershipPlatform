package com.example.intership.dao;

import com.example.intership.entities.discussion.Image;
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

    /*
        保存话题图片函数
        参数为图片image
     */
    public void saveImage(Image image) {
        mongoTemplate.save(image);
    }

    /*
        获取话题图片函数
        参数为图片imageId
        返回值为图片
     */
    public Image getImage(ObjectId imageId) {
        Criteria criteria = Criteria.where("_id").is(imageId);
        Query query = new Query(criteria);

        return mongoTemplate.findOne(query, Image.class);
    }

    /*
        删除话题图片函数
        参数为图片imageId
     */
    public void delImage(ObjectId imageId) {
        Criteria criteria = Criteria.where("_id").is(imageId);
        Query query = new Query(criteria);

        mongoTemplate.remove(query, Image.class);
    }

}
