package com.example.intership.dao;

import com.example.intership.entities.form.Picture;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class PictureTemplate {
    @Autowired
    MongoTemplate mongoTemplate;

    public void savePicture(Picture picture, String colName) {
        mongoTemplate.save(picture, colName);
    }

    public void deletePicture(String account, int role, String colName) {
        Criteria criteria = Criteria.where("account").is(account).and("role").is(role);
        Query query = new Query(criteria);

        mongoTemplate.remove(query, colName);
    }

    /*
        获取头像函数
        参数为账户account和角色role
        返回值为该用户的头像
     */
    public Picture getAvatar(ObjectId pictureId) {
        Criteria criteria = Criteria.where("_id").is(pictureId);
        Query query = new Query(criteria);

        Picture picture = mongoTemplate.findOne(query, Picture.class, "avatar");

        return picture;
    }

    /*
        获取头像id函数
        参数为账户account和角色role
        返回值为该用户的头像id
     */
    public ObjectId getAvatarId(String account, int role) {
        Criteria criteria = Criteria.where("account").is(account).and("role").is(role);
        Query query = new Query(criteria);

        Picture picture = mongoTemplate.findOne(query, Picture.class, "avatar");

        return picture.getId();
    }

    /*
        判断图片是否存在函数
        参数为用户帐号account、角色role和存储表名colName
        返回值为是否存在的布尔值
     */
    public boolean isExist(String account, int role, String colName) {
        Criteria criteria = Criteria.where("account").is(account).and("role").is(role);
        Query query = new Query(criteria);

        Picture picture = mongoTemplate.findOne(query, Picture.class, colName);
        return (picture != null) ? true : false;
    }
}
