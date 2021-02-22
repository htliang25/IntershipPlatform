package com.example.intership.entities;

import org.bson.types.ObjectId;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Accessory {
    private ObjectId _id;
    private String account;
    private int role;
    private String name;
    private byte[] content;
    private String contentType;
    private String url;

    public Accessory(String account) {
        _id = new ObjectId();
        this.account = account;
    }

    public void setAttributes(int role, MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            byte[] content = multipartFile.getBytes();
            setRole(role);
            setName(fileName);
            setContent(content);
            setContentType(suffixName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setRole(int role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ObjectId getId() {
        return _id;
    }

    public Map<String, Object> getFile() {
        Map<String, Object> data = new HashMap<>();

        try {
            InputStream is = new ByteArrayInputStream(content);
            MultipartFile input = new MockMultipartFile(name, is);

            data.put("name", name);
            data.put("content", input);
            data.put("contentType", contentType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public int getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public byte[] getContent() {
        return content;
    }

    public String getContentType() {
        return contentType;
    }
}
