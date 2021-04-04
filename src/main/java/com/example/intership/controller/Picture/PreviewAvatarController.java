package com.example.intership.controller.Picture;

import com.example.intership.entities.form.Picture;
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
        参数为为用户帐号account和用户角色role
        返回值为用户头像图片
     */
    @ResponseBody
    @GetMapping(value = "/avatar/{pictureId}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public byte[] previewAvatar(@PathVariable ObjectId pictureId) {
        Picture picture = pictureService.getAvatar(pictureId);
        return (picture != null) ? picture.getContent() : new byte[0];
    }
}
