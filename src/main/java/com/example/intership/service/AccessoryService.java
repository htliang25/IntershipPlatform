package com.example.intership.service;

import com.example.intership.dao.AccessoryTemplate;
import com.example.intership.entities.Accessory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AccessoryService {
    @Autowired
    AccessoryTemplate accessoryTemplate;

    public ArrayList getAccessory(String account) {
        return accessoryTemplate.getAccessory(account);
    }

    public void saveAccessory(Accessory accessory, String colName) {
        accessoryTemplate.saveAccessory(accessory, colName);
    }

    public void deleteAccessory(String account, int role, String colName) {
        accessoryTemplate.deleteAccessory(account, role, colName);
    }

    public boolean isExist(String account, int role, String colName) {
        return accessoryTemplate.isExist(account, role, colName);
    }

    public Accessory getAvatar(String account, int role) {
        return accessoryTemplate.getAvatar(account, role);
    }
}
