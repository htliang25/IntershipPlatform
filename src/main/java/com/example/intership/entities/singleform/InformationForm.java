package com.example.intership.entities.singleform;

import com.example.intership.entities.Form;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Update;

import java.util.HashMap;
import java.util.Map;

@Document(collection = "informationForm")
public class InformationForm extends Form {
    private String resumeName;
    private String name;
    private Long birthday;
    private String sexy;
    private String address;
    private String educationBackground;
    private String phone;
    private String email;
    private String politics;

    public InformationForm(String account) {
        super(account);
    }

    public void setAttributes(Map<String, Object> data) {
        setResumeName((String) data.get("resumeName"));
        setName((String) data.get("name"));
        if (this.isObjectNotEmpty(data.get("birthday"))) {
            setBrithday((Long) data.get("birthday"));
        }
        setSexy((String) data.get("sexy"));
        setAddress((String) data.get("address"));
        setEducationBackground((String) data.get("educationBackground"));
        setPhone((String) data.get("phone"));
        setEmail((String) data.get("email"));
        setPolitics((String) data.get("politics"));
    }

    public void setResumeName(String resumeName) {
        this.resumeName = resumeName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrithday(Long birthday) {
        this.birthday = birthday;
    }

    public void setSexy(String sexy) {
        this.sexy = sexy;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEducationBackground(String educationBackground) {
        this.educationBackground = educationBackground;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setPolitics(String politics) {
        this.politics = politics;
    }

    public static Update modify(Map<String, Object> data) {
        Update update = new Update();

        update.set("resumeName", (String) data.get("resumeName"));
        update.set("name", (String) data.get("name"));
        update.set("birthday", (String) data.get("brithday"));
        update.set("sexy", (String) data.get("sexy"));
        update.set("address", (String) data.get("address"));
        update.set("educationBackground", (String) data.get("educationBackground"));
        update.set("phone", (String) data.get("phone"));
        update.set("email", (String) data.get("email"));
        update.set("identity", (String) data.get("identity"));
        update.set("politics", (String) data.get("politics"));

        return update;
    }

    public Map<String, Object> getForm() {
        Map<String, Object> data = new HashMap<>();

        data.put("name", name);
        data.put("brithday", birthday);
        data.put("sexy", sexy);
        data.put("address", address);
        data.put("educationBackground", educationBackground);
        data.put("phone", phone);
        data.put("email", email);
        data.put("politics", politics);

        return data;
    }

    public String getName() {
        return name;
    }

    public Long getBrithday() {
        return birthday;
    }

    public String getSexy() {
        return sexy;
    }

    public String getAddress() {
        return address;
    }

    public String getEducationBackground() {
        return educationBackground;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPolitics() {
        return politics;
    }
}
