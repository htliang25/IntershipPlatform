package com.example.intership.entities.resuem.multipleform;

import com.example.intership.entities.resuem.Form;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Document(collection = "educationExperience")
public class EducationExperience extends Form {
    /*
        教育经历类，继承自表格类
        类属性有学历背景background、学校school、专业major、专业排名rank、入学时间inTime和毕业时间outTime
        类函数有类属性的set、get函数
     */
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
        if (this.isObjectNotEmpty(data.get("educationTime"))) {
            setEducationTime((ArrayList) data.get("educationTime"));
        }
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

    public void setEducationTime(ArrayList educationTime) {
        inTime = (String) educationTime.get(0);
        outTime = (String) educationTime.get(1);
    }

    public Map<String, Object> getForm() {
        Map<String, Object> data = new HashMap<>();

        data.put("background", background);
        data.put("school", school);
        data.put("major", major);
        data.put("rank", rank);
        ArrayList arr = new ArrayList();
        arr.add((inTime != null) ? inTime : "");
        arr.add((outTime != null) ? outTime : "");
        data.put("educationTime", arr);

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
