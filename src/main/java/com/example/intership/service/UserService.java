package com.example.intership.service;

import com.example.intership.dao.UserTemplate;
import com.example.intership.entities.user.Enterprise;
import com.example.intership.entities.user.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserTemplate userTemplate;

    public Student getStudent(String name) {
        return userTemplate.getStudent(name);
    }

    public void saveStudent(Student student) {
        userTemplate.saveStudent(student);
    }

    public Enterprise getEnterprise(String name) {
        return userTemplate.getEnterprise(name);
    }

    public void saveEnterprise(Enterprise enterprise) {
        userTemplate.saveEnterprise(enterprise);
    }

    public List<Student> getStudents() {
        return userTemplate.getStudents();
    }

    public List<Enterprise> getEnterprises() {
        return userTemplate.getEnterprises();
    }

    public void modifyPwd(String name, String pwd, String col_name) {
        userTemplate.modifyPwd(name, pwd, col_name);
    }

    public void modifyInfo(String name, String[] str) {
        userTemplate.modifyInfo(name, str);
    }
}
