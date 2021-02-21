package com.example.intership.controller;

import com.example.intership.entities.Accessory;
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

    @ResponseBody
    @GetMapping(value = "/avatar/{account}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public byte[] PreviewAvatar(@PathVariable String account) {
        Accessory accessory = accessoryService.getAvatar(account);

        return (accessory != null) ? accessory.getContent() : new byte[0];
    }
}