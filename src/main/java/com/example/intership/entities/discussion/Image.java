package com.example.intership.entities.discussion;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Document("image")
public class Image {
    /*
        图片类
        用于保存评论区话题图片
        类属性有id、图片字节流content和图片类型contentType
        类函数有类属性的set、get函数和获取图片函数
     */
    private ObjectId _id;
    private byte[] content;
    private String contentType;

    public Image () {
        _id = new ObjectId();
    }

    public void setAttributes(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            byte[] content = multipartFile.getBytes();
            setContent(content);
            setContentType(suffixName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
