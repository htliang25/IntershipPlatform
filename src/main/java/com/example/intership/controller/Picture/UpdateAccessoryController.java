package com.example.intership.controller.Picture;

import com.example.intership.entities.form.Accessory;
import com.example.intership.service.AccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
                                                    @RequestParam(value = "role", required = false) int role,
                                                    @RequestParam(value = "appendixList", required = false) ArrayList<MultipartFile> appendixList) {
        String account = request.getParameter("account");
        if (appendixList != null) {
            // 这里传入 account 和 附件 可以传多个文件了
            if (accessoryService.isExist(account)) {
                accessoryService.deleteAccessory(account);
            }
            int len = appendixList.size();
            for (int i = 0; i < len; i++) {
                Accessory accessory = new Accessory(account, role);
                accessory.setAttributes((MultipartFile) appendixList.get(i));
                accessoryService.saveAccessory(accessory);
            }
        }
        return new HashMap<>();
    }
}
