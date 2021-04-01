package com.example.intership.controller;

import com.example.intership.entities.Image;
import com.example.intership.service.ImageService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ImageController {

    @Autowired
    ImageService imageService;

    @ResponseBody
    @PostMapping("/addArticleImg")
    public Map<String,Object> addArticleImg(HttpServletRequest request,
                                            @RequestParam(value = "image", required = false) MultipartFile file) {

        Map<String, Object> map = new HashMap<>();
        if (file != null) {
            Image image = new Image();
            image.setAttributes(file);
            imageService.saveImage(image);

            map.put("url", "/api/img/" + image.get_id().toString());
            map.put("code", 20001);
        }

        return map;
    }


    @ResponseBody
    @GetMapping(value = "/img/{imgName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public byte[] getArticleImg(@PathVariable String imgName) {

        Image image = imageService.getImage(new ObjectId(imgName));

        return (image!= null) ? image.getContent() : new byte[0];
    }

    @ResponseBody
    @PostMapping("/delArticleImg")
    public Map<String, Object> delArticleImg(@RequestBody Map<String, Object> data) {
        String imageId = (String) data.get("imageId");
        if (imageId != null) {
            imageService.delImage(new ObjectId(imageId));
        }
        return new HashMap<>();
    }


}
