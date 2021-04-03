package com.example.intership.service;

import com.example.intership.dao.AccessoryTemplate;
import com.example.intership.entities.Accessory;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccessoryService {
    @Autowired
    AccessoryTemplate accessoryTemplate;

    public Accessory getAccessoryDetail(String account, String fileName) {
        return accessoryTemplate.getAccessoryDetail(account, fileName);
    }

    public List<Accessory> getMyAccessoryList(String account, int role) {
        return accessoryTemplate.getAccessory(account, role);
    }

    public void saveAccessory(Accessory accessory, String colName) {
        accessoryTemplate.saveAccessory(accessory, colName);
    }

    public void deleteAccessory(String account, int role, String fileName, String colName) {
        accessoryTemplate.deleteAccessory(account, role, fileName, colName);
    }

    public boolean isExist(String account, int role, String fileName, String colName) {
        return accessoryTemplate.isExist(account, role, fileName, colName);
    }

    public void deleteAvatar(String account, int role, String colName) {
        accessoryTemplate.deleteAvatar(account, role, colName);
    }

    public boolean isAvatarExist(String account, int role, String colName) {
        return accessoryTemplate.isAvatarExist(account, role, colName);
    }

    public Accessory getAvatar(String account, int role) {
        return accessoryTemplate.getAvatar(account, role);
    }

    public void removeResumeAccessory (ObjectId accessoryId) {
        accessoryTemplate.removeResumeAccessory(accessoryId);
    }

}

