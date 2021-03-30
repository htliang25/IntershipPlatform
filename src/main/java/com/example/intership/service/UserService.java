package com.example.intership.service;

import com.example.intership.dao.UserTemplate;
import com.example.intership.entities.User;
import com.example.intership.entities.user.Enterprise;
import com.example.intership.entities.user.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserTemplate userTemplate;

    public User getUser(String account, int role) {
        return userTemplate.getUser(account, role);
    }

    public List<Student> getStudentList() {
        return userTemplate.getStudentList();
    }

    public void saveUser(User user) {
        userTemplate.saveUser(user);
    }

    public void modifyPwd(String name, String pwd, String col_name) {
        userTemplate.modifyPwd(name, pwd, col_name);
    }

    public void modifyInfo(String name, String[] str) {
        userTemplate.modifyInfo(name, str);
    }

    public List<Enterprise> searchEnterprise (String searchKey) {
        return userTemplate.searchEnterprise(searchKey);
    }

    public List<Enterprise> getEnterpriseList () {
        return userTemplate.getEnterpriseList();
    }



}
