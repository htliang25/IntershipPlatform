package com.example.intership.service;

import com.example.intership.dao.PictureTemplate;
import com.example.intership.entities.form.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PictureService {
    @Autowired
    PictureTemplate pictureTemplate;

    public ArrayList getAccessories(String account) {
        return pictureTemplate.getAccessories(account);
    }

    public void saveAccessory(Picture picture, String colName) {
        pictureTemplate.saveAccessory(picture, colName);
    }

    public void deleteAccessory(String account, int role, String colName) {
        pictureTemplate.deleteAccessory(account, role, colName);
    }

    public boolean isExist(String account, int role, String colName) {
        return pictureTemplate.isExist(account, role, colName);
    }

    public Picture getAvatar(String account, int role) {
        return pictureTemplate.getAvatar(account, role);
    }
}
