package com.example.intership.entities.user;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "enterprise")
public class Enterprise extends User implements Comparable{
    /*
        企业类，继承自用户类
        类属性有公司名称companyName、公司介绍companyIntro、公司类型companyType和公司地址companyAddress
        类函数有类属性的set、get函数
     */
    private String companyName;
    private String companyIntro;
    private String companyType;
    private String companyAddress;

    private ArrayList<ObjectId> jobList;
    private int jobCount;

    public Enterprise() {
        super();
        super.setRole(2);
        jobList = new ArrayList<>();
        jobCount = 0;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyIntro(String companyIntro) {
        this.companyIntro = companyIntro;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyIntro() {
        return companyIntro;
    }

    public ArrayList<ObjectId> getJobList() { return jobList; }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String type) {
        this.companyType = type;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String address) {
        this.companyAddress = address;
    }

    public int getJobCount() {
        return jobCount;
    }

    public boolean JobIsExist(ObjectId jobId) {
        return (jobList.indexOf(jobId) == -1) ? true : false;
    }

    @Override
    public int compareTo(Object o) {
        Enterprise e = (Enterprise) o;
        if (this.jobCount < e.getJobCount())
            return 1;
        else if (this.jobCount > e.getJobCount())
            return -1;
        else
            return 0;
    }
}
