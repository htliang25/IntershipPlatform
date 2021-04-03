package com.example.intership.entities.form.multipleform;

import com.example.intership.entities.form.Form;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document(collection = "schoolExperience")
public class SchoolExperience extends Form {
    /*
        校内经历类，继承自表格类
        类属性有组织organization、职位position、开始时间inTime、结束时间outTime和描述content
        类函数有类属性的set、get函数
     */
    private String organization;
    private String position;
    private String inTime;
    private String outTime;
    private String content;

    public SchoolExperience(String account) {
        super(account);
    }

    public void setAttributes(Map<String, Object> data) {
        setOrganization((String) data.get("organization"));
        setPosition((String) data.get("position"));
        setInTime((String) data.get("inTime"));
        setOutTime((String) data.get("outTime"));
        setContent((String) data.get("content"));
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, Object> getForm() {
        Map<String, Object> data = new HashMap<>();

        data.put("organization", organization);
        data.put("position", position);
        data.put("inTime", inTime);
        data.put("outTime", outTime);
        data.put("content", content);

        return data;
    }

    public String getOrganization() {
        return organization;
    }

    public String getPosition() {
        return position;
    }

    public String getInTime() {
        return inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public String getContent() {
        return content;
    }
}
