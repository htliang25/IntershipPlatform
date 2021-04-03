package com.example.intership.entities.user;

import org.bson.types.ObjectId;

public class User {
    /*
        用户类
        继承类有学生类和企业类
        类属性有id、账户account、密码pwd和角色role
        类函数有类属性的set、get函数
     */
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
