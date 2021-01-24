package com.example.intership.entities;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="user")
public class User {
    private ObjectId _id;
    private String name;
    private String pwd;
    private String university;
    private String major;
    private int role;

    public User(){
        _id = new ObjectId();
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPwd(String pwd){
        this.pwd = pwd;
    }

    public void setUniversity(String university) {this.university = university;}

    public void setMajor(String major) {this.major = major;}

    public void setRole(int role){
        this.role = role;
    }

    public String getName(){
        return name;
    }

    public String getPwd(){
        return pwd;
    }

    public String getUniversity() {return university;}

    public String getMajor() {return major;}

    public int getRole(){
        return role;
    }
}
