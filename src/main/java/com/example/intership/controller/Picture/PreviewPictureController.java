package com.example.intership.controller.Picture;

import com.example.intership.entities.form.Picture;
import com.example.intership.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class PreviewPictureController {
    @Autowired
    PictureService pictureService;

    /*
        获取用户头像函数
        api为图片的url
        参数为为用户帐号account和用户角色role
        返回值为用户头像图片
     */
    @ResponseBody
    @GetMapping(value = "/avatar/{role}/{account}/{random}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public byte[] previewAvatar(@PathVariable int role, @PathVariable String account) {
        Picture picture = pictureService.getAvatar(account, role);

        return (picture != null) ? picture.getContent() : new byte[0];
    }


    // 图片
    @ResponseBody
    @GetMapping(value = "/accessory/img/{account}/{fileName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public byte[] previewImgAccessory(@PathVariable String account, @PathVariable String fileName) {
        Accessory accessory = accessoryService.getAccessoryDetail(account, fileName);

        return (accessory != null) ? accessory.getContent() : new byte[0];
    }

    // pdf文件
    @ResponseBody
    @GetMapping(value = "/accessory/pdf/{account}/{fileName}", produces = {MediaType.APPLICATION_PDF_VALUE})
    public byte[] previewPdfAccessory(@PathVariable String account, @PathVariable String fileName) {
        Accessory accessory = accessoryService.getAccessoryDetail(account, fileName);

        return (accessory != null) ? accessory.getContent() : new byte[0];
    }



}
