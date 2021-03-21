package com.example.intership.entities.user;

import com.example.intership.entities.Applicant;
import com.example.intership.entities.User;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "enterprise")
public class Enterprise extends User {
    private String companyName;
    private String companyIntro;
    private String companyType;
    private String companyAddress;

    private ArrayList<Applicant> applicants;

    public Enterprise() {
        super();
        super.setRole(2);
        applicants = new ArrayList<Applicant>();
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyIntro(String companyIntro) {
        this.companyIntro = companyIntro;
    }

    public void setApplicants(ArrayList<Applicant> applicants) { this.applicants = applicants; }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyIntro() {
        return companyIntro;
    }

    public ArrayList<Applicant> getApplicants() { return applicants; }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String type) {
        this.companyType = type;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String address) {
        this.companyAddress = address;
    }
}
