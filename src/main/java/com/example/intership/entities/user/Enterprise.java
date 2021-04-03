package com.example.intership.entities.user;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "enterprise")
public class Enterprise extends User {
    /*
        企业类，继承自用户类
        类属性有公司名称companyName、公司介绍companyIntro、公司类型companyType和公司地址companyAddress
        类函数有类属性的set、get函数
     */
    private String companyName;
    private String companyIntro;
    private String companyType;
    private String companyAddress;

    private ArrayList<Applicant> applicants;

    public Enterprise() {
        super();
        super.setRole(2);
        applicants = new ArrayList<>();
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyIntro(String companyIntro) {
        this.companyIntro = companyIntro;
    }

    public void setApplicants(ArrayList<Applicant> applicants) { this.applicants = applicants; }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyIntro() {
        return companyIntro;
    }

    public ArrayList<Applicant> getApplicants() { return applicants; }

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
}
