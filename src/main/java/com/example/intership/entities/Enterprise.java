package com.example.intership.entities;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "enterprise")
public class Enterprise extends User{
    private String companyName;
    private String companyIntro;

    public Enterprise() {
        super();
        super.setRole(2);
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyIntro(String companyIntro) {
        this.companyIntro = companyIntro;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyIntro() {
        return companyIntro;
    }
}
