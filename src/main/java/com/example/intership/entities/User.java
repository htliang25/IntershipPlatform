package com.example.intership.entities;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

public class User {
    private ObjectId _id;
    private String account;
    private String pwd;
    private int role;

    public User() {
        _id = new ObjectId();
    }

    public void setAccount(String name) {
        this.account = name;
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

    public String getAccount() {
        return account;
    }

    public String getPwd() {
        return pwd;
    }

    public int getRole() {
        return role;
    }
}
