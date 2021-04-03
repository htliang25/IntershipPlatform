package com.example.intership.dao;

import com.example.intership.entities.form.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PictureTemplate {
    @Autowired
    MongoTemplate mongoTemplate;

    /*
        获取附件函数
        参数为账户account
        返回值为该用户的附件列表
     */
    public ArrayList getAccessories(String account) {
        Criteria criteria = Criteria.where("account").is(account);
        Query query = new Query(criteria);

        ArrayList data = new ArrayList();

        List<Picture> accessories = mongoTemplate.find(query, Picture.class, "accessory");
        for (Picture picture : accessories) {
            data.add(picture.getPicture());
        }

        return data;
    }

    /*
        获取头像函数
        参数为账户account和角色role
        返回值为该用户的头像
     */
    public Picture getAvatar(String account, int role) {
        Criteria criteria = Criteria.where("account").is(account).and("role").is(role);
        Query query = new Query(criteria);

        Picture picture = mongoTemplate.findOne(query, Picture.class, "avatar");

        return picture;
    }

    /*
        保存附件函数
        参数为附件图像picture和存储表名colName
     */
    public void saveAccessory(Picture picture, String colName) {
        mongoTemplate.save(picture, colName);
    }

    /*
        删除附件函数
        参数为用户帐号account、角色role和存储表名colName
     */
    public void deleteAccessory(String account, int role, String colName) {
        Criteria criteria = Criteria.where("account").is(account).and("role").is(role);
        Query query = new Query(criteria);

        mongoTemplate.remove(query, Picture.class, colName);
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
