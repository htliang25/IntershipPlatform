package com.example.intership.service;

import com.example.intership.dao.PictureTemplate;
import com.example.intership.entities.resuem.Picture;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureService {
    @Autowired
    PictureTemplate pictureTemplate;

    public void savePicture(Picture picture, String colName) {
        pictureTemplate.savePicture(picture, colName);
    }

    public void deletePicture(String account, int role, String colName) {
        pictureTemplate.deletePicture(account, role, colName);
    }

    public boolean isExist(String account, int role, String colName) {
        return pictureTemplate.isExist(account, role, colName);
    }

    public Picture getAvatar(ObjectId pictureId) {
        return pictureTemplate.getAvatar(pictureId);
    }

    public ObjectId getAvatarId(String account, int role) {
        return pictureTemplate.getAvatarId(account, role);
    }
}
