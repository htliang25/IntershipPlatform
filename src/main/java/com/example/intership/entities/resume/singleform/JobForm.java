package com.example.intership.entities.resume.singleform;

import com.example.intership.entities.resume.Form;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Update;

import java.util.HashMap;
import java.util.Map;

@Document(collection = "jobForm")
public class JobForm extends Form {
    /*
        求职意向类，继承自表格类
        类属性有工作地点address和职位job
        类函数有类属性的set、get函数和修改函数
     */
    private String address;
    private String job;

    public JobForm(String account) {
        super(account);
    }

    public void setAttributes(Map<String, Object> data) {
        setAddress((String) data.get("address"));
        setJob((String) data.get("job"));
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public static Update modify(Map<String, Object> data) {
        Update update = new Update();

        update.set("address", (String) data.get("address"));
        update.set("job", (String) data.get("job"));

        return update;
    }

    public Map<String, Object> getForm() {
        Map<String, Object> data = new HashMap<>();

        data.put("address", address);
        data.put("job", job);

        return data;
    }

    public String getAddress() {
        return address;
    }

    public String getJob() {
        return job;
    }
}
