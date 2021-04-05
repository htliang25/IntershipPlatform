package com.example.intership.entities.resume;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Update;

public class Content {
    /*
        内容类
        类属性有id、账户account和内容content
        类函数有设置内容函数、获取内容函数和修改内容函数
     */
    private ObjectId _id;
    private String account;
    private String content;

    public Content(String account) {
        _id = new ObjectId();
        this.account = account;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public static Update modifyContent(String content) {
        Update update = new Update();

        update.set("content", content);

        return update;
    }
}
