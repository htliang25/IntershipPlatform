package com.example.intership.controller.Picture;

import com.example.intership.entities.resuem.Picture;
import com.example.intership.service.PictureService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PreviewAvatarController {
    @Autowired
    PictureService pictureService;

    /*
        获取用户头像函数
        api为图片的url
        参数为为头像avatarId
        返回值为用户头像图片
     */
    @ResponseBody
    @GetMapping(value = "/avatar/{avatarId}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public byte[] previewAvatar(@PathVariable String avatarId) {
        ObjectId id = new ObjectId(avatarId);
        Picture picture = pictureService.getAvatar(id);
        return (picture != null) ? picture.getContent() : new byte[0];
    }
}
