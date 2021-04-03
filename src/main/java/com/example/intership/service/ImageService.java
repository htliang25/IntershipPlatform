package com.example.intership.service;

import com.example.intership.dao.ImageTemplate;
import com.example.intership.entities.discussion.Image;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    ImageTemplate imageTemplate;

    public void saveImage(Image image) {
        imageTemplate.saveImage(image);
    }

    public Image getImage(ObjectId id) {
        return imageTemplate.getImage(id);
    }

    public void delImage(ObjectId imageId) {
        imageTemplate.delImage(imageId);
    }

}
