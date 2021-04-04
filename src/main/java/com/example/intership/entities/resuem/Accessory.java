package com.example.intership.entities.resuem;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Document(collection = "accessory")
public class Accessory {
    private ObjectId id;
    private String account;
    private String name;
    private byte[] content;
    private String contentType;
    private String url;

    public Accessory(String account) {
        id = new ObjectId();
        this.account = account;
    }

    public void setAttributes(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            byte[] content = multipartFile.getBytes();
            setName(fileName);
            setContent(content);
            setContentType(suffixName);
            if (suffixName.equals(".pdf")) {
                setUrl("/api/accessory/pdf/" + account + '/' + fileName);
            } else {
                setUrl("/api/accessory/img/" + account + '/' + fileName);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
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
        return id;
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

    public String getName() {
        return name;
    }

    public byte[] getContent() {
        return content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUrl() {
        return url;
    }
}
