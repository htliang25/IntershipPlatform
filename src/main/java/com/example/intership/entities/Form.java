package com.example.intership.entities;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
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

    public ObjectId getId() {
        return _id;
    }

    public String getAccount() {
        return account;
    }

    public static boolean isObjectNotEmpty(Object obj) {
        String str = ObjectUtils.toString(obj, "");
        return StringUtils.isNotBlank(str);
    }
}
