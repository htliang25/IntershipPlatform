package com.example.intership.entities.resuem.multipleform;

import com.example.intership.entities.resuem.Form;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document(collection = "awardExperience")
public class AwardExperience extends Form {
    /*
        获奖情况类，继承自表格类
        类属性有奖项名name和获奖时间time
        类函数有类属性的set、get函数
     */
    private String name;
    private String time;

    public AwardExperience(String account) {
        super(account);
    }

    public void setAttributes(Map<String, Object> data) {
        setName((String) data.get("name"));
        setTime((String) data.get("time"));
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Map<String, Object> getForm() {
        Map<String, Object> data = new HashMap<>();

        data.put("name", name);
        data.put("time", time);

        return data;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }
}
