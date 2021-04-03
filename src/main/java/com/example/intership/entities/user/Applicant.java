package com.example.intership.entities.user;

public class Applicant {
    /*
        申请者类
        类属性有所申请工作id、申请工作名jobName、申请者账号applicantAccount和入职时间entryTime
        类函数有类属性的set、get函数
     */
    private String jobId;
    private String jobName;
    private String applicantAccount;
    private String entryTime;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
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
