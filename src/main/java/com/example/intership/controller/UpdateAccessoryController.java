package com.example.intership.controller;

import com.example.intership.entities.Accessory;
import com.example.intership.service.AccessoryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
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
        Map<String, Object> map = new HashMap<>();
        map.put("code", 20001);
        return map;
    }

    @ResponseBody
    @PostMapping(value = {"/UserUpdateResumeAvatar", "/CompanyUpdateLogo"})
    public Map<String, Object> updateResumeAvatar(HttpServletRequest request,
                                                  @RequestParam(value = "role", required = false) int role,
                                                  @RequestParam(value = "avatar", required = false) MultipartFile file) {
        String account = request.getParameter("account");
        if (file != null) {
            // 这里传入account 和 简历头像
            updateAvatar(account, role, file, "avatar");
        }

        Map<String, Object> map = new HashMap<>();
        return new HashMap<>();
    }

    /*
    上传个人简历附件作品, 之前的代码会把之前上传的附件都删除，这是不符合预期的
    现在改成只删除同样名字的附件
     */
    public void updateAccessory(String account, ArrayList list, String colName) {
        int len = list.size();
        for (int i = 0; i < len; i++) {
            MultipartFile file = (MultipartFile) list.get(i);
            if (accessoryService.isExist(account, 1, file.getOriginalFilename(), colName)) {
                accessoryService.deleteAccessory(account, 1, file.getOriginalFilename(), colName);
            }

            Accessory accessory = new Accessory(account);
            accessory.setAttributes(1, (MultipartFile) list.get(i));
            accessoryService.saveAccessory(accessory, "accessory");
        }
    }

    /*
    更新个人和企业头像
    头像和附件的原理不应该是一样的，访问频率不一样的话，千万不能放在同一个数据库里, 没必要强行复用代码
     */
    public void updateAvatar(String account, int role, MultipartFile file, String colName) {
        if (accessoryService.isAvatarExist(account, role,colName)) {
            accessoryService.deleteAvatar(account, role, colName);
        }
        Accessory accessory = new Accessory(account);
        accessory.setAttributes(role, file);
        accessoryService.saveAccessory(accessory, colName);
    }



    // 用户针对某个附件id进行删除
    @ResponseBody
    @PostMapping("/UserRemoveResumeAppendix")
    public Map<String, Object> removeResumeAppendix (@RequestBody Map<String, Object> data) {
        String accessoryId = (String) data.get("accessoryId");

        accessoryService.removeResumeAccessory(new ObjectId(accessoryId));

        Map<String, Object> map = new HashMap<>();
        map.put("code", 20001);
        return map;
    }


}
