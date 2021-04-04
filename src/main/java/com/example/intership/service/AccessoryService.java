package com.example.intership.service;

import com.example.intership.dao.AccessoryTemplate;
import com.example.intership.entities.form.Accessory;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessoryService {
    @Autowired
    AccessoryTemplate accessoryTemplate;

    public Accessory getAccessoryDetail(String account, String fileName) {
        return accessoryTemplate.getAccessoryDetail(account, fileName);
    }

    public List<Accessory> getMyAccessoryList(String account) {
        return accessoryTemplate.getAccessory(account);
    }

    public void saveAccessory(Accessory accessory) {
        accessoryTemplate.saveAccessory(accessory);
    }

    public void deleteAccessory(String account) {
        accessoryTemplate.deleteAccessory(account);
    }

    public boolean isExist(String account) {
        return accessoryTemplate.isExist(account);
    }

    public void removeResumeAccessory (ObjectId accessoryId) {
        accessoryTemplate.removeResumeAccessory(accessoryId);
    }
}
