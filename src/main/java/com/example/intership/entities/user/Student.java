package com.example.intership.entities.user;

import com.example.intership.entities.Applicant;
import com.example.intership.entities.User;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "student")
public class Student extends User {
    private String university;
    private String major;
    private ArrayList<Applicant> applicants;

    public Student() {
        super();
        super.setRole(1);
        applicants = new ArrayList<>();
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

    public ArrayList<Applicant> getApplicants() {
        return applicants;
    }

    public void setApplicants(ArrayList<Applicant> applicants) {
        this.applicants = applicants;
    }
}
