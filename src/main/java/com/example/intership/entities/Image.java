package com.example.intership.entities;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Document("image")
public class Image {

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
