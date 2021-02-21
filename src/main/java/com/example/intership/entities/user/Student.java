package com.example.intership.entities.user;

import com.example.intership.entities.User;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "student")
public class Student extends User {
    private String university;
    private String major;

    public Student() {
        super();
        super.setRole(1);
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getUniversity() {
        return university;
    }

    public String getMajor() {
        return major;
    }
}
