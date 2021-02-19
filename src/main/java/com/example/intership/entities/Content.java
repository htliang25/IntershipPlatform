package com.example.intership.entities;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;

public abstract class Content {
    private ObjectId _id;
    private String account;

    public Content(String account) {
        _id = new ObjectId();
        this.account = account;
    }

    public abstract void setContent(String content);

    public abstract String getContent();

    public static boolean isObjectNotEmpty(Object obj) {
        String str = ObjectUtils.toString(obj, "");
        return StringUtils.isNotBlank(str);
    }
}
