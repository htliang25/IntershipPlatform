package com.example.intership.controller.Resume;

import com.example.intership.entities.resume.Accessory;
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

    /*
        更新简历附件函数
        api为UserUpdateResumeAppendix
        参数为为用户帐号account、用户角色role和附件列表appendixList
        返回值为
     */
    @ResponseBody
    @PostMapping("/UserUpdateResumeAppendix")
    public Map<String, Object> updateResumeAppendix(HttpServletRequest request,
                                                    @RequestParam(value = "appendixList", required = false) ArrayList<MultipartFile> appendixList) {
        Map<String, Object> res = new HashMap<>();
        String account = request.getParameter("account");
        if (appendixList != null) {
            // 这里传入 account 和 附件 可以传多个文件了
            int len = appendixList.size();
            for (int i = 0; i < len; i++) {
                String name = appendixList.get(i).getOriginalFilename();
                if (accessoryService.isExist(account, name)) {
                    accessoryService.deleteAccessory(account, name);
                }
                Accessory accessory = new Accessory(account);
                accessory.setAttributes(appendixList.get(i));
                accessoryService.saveAccessory(accessory);
            }
        }
        res.put("code", 20001);
        return res;
    }

    @ResponseBody
    @PostMapping("/UserRemoveResumeAppendix")
    public Map<String, Object> removeResumeAppendix(@RequestBody Map<String, Object> data) {
        Map<String, Object> res = new HashMap<>();
        ObjectId id = new ObjectId((String) data.get("accessoryId"));
        accessoryService.removeResumeAccessory(id);
        res.put("code", 20001);
        return res;
    }
}
