package com.example.intership.entities.multipleform;

import com.example.intership.entities.Form;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document(collection = "educationExperience")
public class EducationExperience extends Form {
    private String background;
    private String school;
    private String major;
    private String rank;
    private String inTime;
    private String outTime;

    public EducationExperience(String account) {
        super(account);
    }

    public void setAttributes(Map<String, Object> data) {
        setBackground((String) data.get("background"));
        setSchool((String) data.get("school"));
        setMajor((String) data.get("major"));
        setRank((String) data.get("rank"));
        setInTime((String) data.get("inTime"));
        setOutTime((String) data.get("outTime"));
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public Map<String, Object> getForm() {
        Map<String, Object> data = new HashMap<>();

        data.put("background", background);
        data.put("school", school);
        data.put("major", major);
        data.put("rank", rank);
        data.put("inTime", inTime);
        data.put("outTime", outTime);

        return data;
    }

    public String getBackground() {
        return background;
    }

    public String getSchool() {
        return school;
    }

    public String getMajor() {
        return major;
    }

    public String getRank() {
        return rank;
    }

    public String getInTime() {
        return inTime;
    }

    public String getOutTime() {
        return outTime;
    }
}
