package com.example.intership.service;

import com.example.intership.dao.UserTemplate;
import com.example.intership.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void saveUser(User user){
        userTemplate.saveUser(user);
    }
}
