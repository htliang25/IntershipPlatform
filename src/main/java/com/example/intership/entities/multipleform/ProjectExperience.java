package com.example.intership.entities.multipleform;

import com.example.intership.entities.Form;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document(collection = "projectExperience")
public class ProjectExperience extends Form {
    private String name;
    private String position;
    private String inTime;
    private String outTime;
    private String content;

    public ProjectExperience(String account) {
        super(account);
    }

    public void setAttributes(Map<String, Object> data) {
        setName((String) data.get("name"));
        setPosition((String) data.get("position"));
        setInTime((String) data.get("inTime"));
        setOutTime((String) data.get("outTime"));
        setContent((String) data.get("content"));
    }

    public void setName(String name) {
        this.name = name;
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

        data.put("name", name);
        data.put("position", position);
        data.put("inTime", inTime);
        data.put("outTime", outTime);
        data.put("content", content);

        return data;
    }

    public String getName(String name) {
        return name;
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
