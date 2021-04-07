package com.example.intership.entities.job;

import org.bson.types.ObjectId;

public class Applicant {
    /*
        申请者类
        类属性有申请工作jobId、申请者账号applicantAccount和入职时间entryTime
        类函数有类属性的set、get函数
     */
    private ObjectId _id;
    private String jobId;
    private String applicantAccount;
    private String entryTime;

    public Applicant() {
        _id = new ObjectId();
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getApplicantAccount() {
        return applicantAccount;
    }

    public void setApplicantAccount(String applicantAccount) {
        this.applicantAccount = applicantAccount;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

}
