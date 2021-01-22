package com.example.intership.service;

import com.example.intership.dao.UserTemplate;
import com.example.intership.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserTemplate userTemplate;

    public List<User> getUsers() {
        return userTemplate.getUsers();
    }

    public User getUser(String name) {
        return userTemplate.getuser(name);
    }

    public void saveUser(String name, String pwd, int role){
        User user = new User();

        user.setName(name);
        user.setPwd(pwd);
        user.setRole(role);

        userTemplate.saveUser(user);
    }
}
