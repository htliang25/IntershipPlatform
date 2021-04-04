package com.example.intership.service;

import com.example.intership.dao.ResumeTemplate;
import com.example.intership.entities.resuem.Content;
import com.example.intership.entities.resuem.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class ResumeService {
    @Autowired
    ResumeTemplate resumeTemplate;

    public void saveForm(Form form, String colName) {
        resumeTemplate.saveForm(form, colName);
    }

    public void saveContent(Content content, String colName) {
        resumeTemplate.saveContent(content, colName);
    }

    public Map<String, Object> getSingleForm(String account, String colName) {
        return resumeTemplate.getSingleForm(account, colName);
    }

    public void modifySingleForm(String account, String colName, Map<String, Object> data) {
        resumeTemplate.modifySingleForm(account, colName, data);
    }

    public String getContent(String account, String colName) {
        return resumeTemplate.getContent(account, colName);
    }

    public void modifyContent(String account, String colName, String data) {
        resumeTemplate.modifyContent(account, colName, data);
    }

    public ArrayList getMultipleForm(String account, String colName) {
        return resumeTemplate.getMultipleForm(account, colName);
    }

    public void deleteInfo(String account, String colName) {
        resumeTemplate.deleteInfo(account, colName);
    }

    public boolean isExist(String account, String colName) {
        return resumeTemplate.isExist(account, colName);
    }


}
