package com.example.intership.controller;

import com.example.intership.entities.Accessory;
import com.example.intership.service.AccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UpdateAccessoryController {
    @Autowired
    AccessoryService accessoryService;

    @ResponseBody
    @PostMapping("/UserUpdateResumeAppendix")
    public Map<String, Object> updateResumeAppendix(HttpServletRequest request,
                                                    @RequestParam(value = "appendixList", required = false) ArrayList<MultipartFile> appendixList) {
        String account = request.getParameter("account");
        if (appendixList != null) {
            // 这里传入account 和 附件 可以传多个文件了
            updateAccessory(account, appendixList, "accessory");
        }
        return new HashMap<>();
    }

    @ResponseBody
    @PostMapping("/UserUpdateResumeAvatar")
    public Map<String, Object> updateResumeAvatar(HttpServletRequest request,
                                                  @RequestParam(value = "avatar", required = false) MultipartFile file) {
        String account = request.getParameter("account");
        if (file != null) {
            // 这里传入account 和 简历头像
            updateAvatar(account, file, "avatar");
        }

        return new HashMap<>();
    }

    //更新个人附件作品
    public void updateAccessory(String account, ArrayList list, String colName) {
        if (accessoryService.isExist(account, colName)) {
            accessoryService.deleteAccessory(account, colName);
        }
        int len = list.size();
        for (int i = 0; i < len; i++) {
            Accessory accessory = new Accessory(account);
            accessory.setAttributes((MultipartFile) list.get(i));
            accessoryService.saveAccessory(accessory, "accessory");
        }
    }

    //更新个人和企业头像
    public void updateAvatar(String account, MultipartFile file, String colName) {
        if (accessoryService.isExist(account, colName)) {
            accessoryService.deleteAccessory(account, colName);
        }
        Accessory accessory = new Accessory(account);
        accessory.setAttributes(file);
        accessoryService.saveAccessory(accessory, colName);
    }
}
