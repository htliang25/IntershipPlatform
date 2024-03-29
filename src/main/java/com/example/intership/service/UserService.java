package com.example.intership.service;

import com.example.intership.dao.UserTemplate;
import com.example.intership.entities.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserTemplate userTemplate;

    public User getUser(String account, int role) {
        return userTemplate.getUser(account, role);
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
}
