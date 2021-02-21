package com.example.intership.entities;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

public class User {
    private ObjectId _id;
    private String name;
    private String pwd;
    private int role;

    public User() {
        _id = new ObjectId();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public ObjectId getId() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getPwd() {
        return pwd;
    }

    public int getRole() {
        return role;
    }
}
