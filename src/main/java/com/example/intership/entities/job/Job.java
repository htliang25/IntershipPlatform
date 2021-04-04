package com.example.intership.entities.job;

import com.example.intership.entities.resuem.Form;
import com.example.intership.entities.user.Applicant;
import com.example.intership.service.PictureService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Document(collection = "job")
public class Job extends Form {

    @Autowired
    PictureService pictureService;

    /*
        岗位类，继承自表格类
        用于保存工作岗位
        类属性有公司名companyName、岗位名jobName、工作城市jobCity、工作类型jobType、补充信息jobDescription、工作职责jobDuty和工作要求jobRequire
        类函数有类属性的set、get函数和获取工作详细信息函数
     */
    private String companyName;
    private String jobName;
    private String jobCity;
    private String jobType;
    private String jobDescription;
    private String jobDuty;
    private String jobRequire;

    private ArrayList<Applicant> applicants;

    public Job(String account, String companyName) {
        super(account);
        this.companyName = companyName;
        applicants = new ArrayList<>();
    }

    public void setAttributes(Map<String, Object> data) {
        setJobName((String) data.get("jobName"));
        setJobCity((String) data.get("jobCity"));
        setJobType((String) data.get("jobType"));
        setJobDescription((String) data.get("jobDesc"));
        setJobDuty((String) data.get("jobDuty"));
        setJobRequire((String) data.get("jobRequire"));
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setJobCity(String jobCity) {
        this.jobCity = jobCity;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public void setJobDuty(String jobDuty) {
        this.jobDuty = jobDuty;
    }

    public void setJobRequire(String jobRequire) {
        this.jobRequire = jobRequire;
    }

    public static Update modifyJob(Map<String, Object> data) {
        Update update = new Update();
        update.set("jobName", (String) data.get("jobName"));
        update.set("jobCity", (String) data.get("city"));
        update.set("jobType", (String) data.get("type"));
        update.set("jobDescription", (String) data.get("jobDesc"));
        update.set("jobDuty", (String) data.get("jobDuty"));
        update.set("jobRequire", (String) data.get("jobRequire"));

        return update;
    }

    public Map<String, Object> getForm() {
        Map<String, Object> data = new HashMap<>();

        ObjectId id = super.getId();
        data.put("id", id.toString());
        data.put("jobName", jobName);
        data.put("companyName", companyName);
        data.put("jobDesc", jobDescription);

        return data;
    }

    public Map<String, Object> getFormAddJobDesc() {
        Map<String, Object> data = new HashMap<>();

        ObjectId id = super.getId();
        data.put("id", id.toString());
        data.put("jobName", jobName);
        data.put("companyName", companyName);
        data.put("jobDesc", jobDescription);
        data.put("jobType", jobType);
        data.put("jobCity", jobCity);
        data.put("jobRequire", jobRequire);
        data.put("jobDuty", jobDuty);

        return data;
    }

    public String getAccount() {
        return super.getAccount();
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getJobName() {
        return jobName;
    }

    public String getJobCity() {
        return jobCity;
    }

    public String getJobType() {
        return jobType;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public String getJobDuty() {
        return jobDuty;
    }

    public String getJobRequire() {
        return jobRequire;
    }

    public ArrayList getApplicants() {
        return applicants;
    }
}
