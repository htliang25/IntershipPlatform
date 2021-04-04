package com.example.intership.entities.user;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "student")
public class Student extends User {
    /*
        学生类,继承自用户类
        类属性有现所属大学university、现所读专业major和申请工作列表applicants
        类函数有类属性的set、get函数
     */
    private String university;
    private String major;
    private ArrayList<ObjectId> jobList;

    public Student() {
        super();
        super.setRole(1);
        jobList = new ArrayList<>();
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getUniversity() {
        return university;
    }

    public String getMajor() {
        return major;
    }

    public ArrayList<ObjectId> getJobList() {
        return jobList;
    }

    public boolean JobIsExist(ObjectId jobId) {
        return (jobList.indexOf(jobId) == -1) ? true : false;
    }
}
