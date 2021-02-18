package com.example.intership.entities;

import org.bson.types.ObjectId;

import java.util.Map;

public abstract class Form {
    private ObjectId _id;
    private String account;

    public Form(String account) {
        _id = new ObjectId();
        this.account = account;
    }

    public abstract void setAttributes(Map<String, Object> data);

    public abstract Map<String, Object> getForm();
}
