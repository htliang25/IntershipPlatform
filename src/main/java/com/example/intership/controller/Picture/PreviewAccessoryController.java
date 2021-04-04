package com.example.intership.controller.Picture;

import com.example.intership.entities.resuem.Accessory;
import com.example.intership.service.AccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class PreviewAccessoryController {
    @Autowired
    AccessoryService accessoryService;

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
