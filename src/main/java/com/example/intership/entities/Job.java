package com.example.intership.entities;

import com.example.intership.entities.user.Enterprise;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Document(collection = "job")
public class Job extends Form {
    private String companyName;
    private String jobName;
    private String jobCity;
    private String jobType;
    private String jobDescription;
    private String jobDuty;
    private String jobRequire;

    private ArrayList applicants;

    public Job(String account, String companyName) {
        super(account);
        this.companyName = companyName;
        applicants = new ArrayList();
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
        data.put("logoURL", "http://localhost:8089/avatar/2/" + super.getAccount());
        data.put("companyName", companyName);

        return data;
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
