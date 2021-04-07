package com.example.intership.entities.message;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "message")
public class Message {

    /*
    消息类
    用于保存企业对投递学生的回复
    类属性有岗位id jobId、投递申请学生账号applicantAccount和回复消息replyMsg
    类函数有类属性的set、get函数
    */

    private ObjectId id;
    private String jobId;
    private String applicantAccount;
    private String replyMsg;
    private boolean read;

    public Message () {
        id = new ObjectId();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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

    public String getReplyMsg() {
        return replyMsg;
    }

    public void setReplyMsg(String replyMsg) {
        this.replyMsg = replyMsg;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
