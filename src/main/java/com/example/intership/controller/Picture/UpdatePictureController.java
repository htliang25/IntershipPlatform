package com.example.intership.controller.Picture;

import com.example.intership.entities.form.Picture;
import com.example.intership.service.PictureService;
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
public class UpdatePictureController {
    @Autowired
    PictureService pictureService;

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
            if (pictureService.isExist(account, role, "accessory")) {
                pictureService.deleteAccessory(account, role, "accessory");
            }
            int len = appendixList.size();
            for (int i = 0; i < len; i++) {
                Picture picture = new Picture(account, role);
                picture.setAttributes((MultipartFile) appendixList.get(i));
                pictureService.saveAccessory(picture, "accessory");
            }
        }
        return new HashMap<>();
    }

    /*
        更新用户头像函数
        api为UserUpdateResumeAvatar和CompanyUpdateLogo
        参数为为用户帐号account、用户角色role和头像图片avatar
        返回值为
     */
    @ResponseBody
    @PostMapping(value = {"/UserUpdateResumeAvatar", "/CompanyUpdateLogo"})
    public Map<String, Object> updateResumeAvatar(HttpServletRequest request,
                                                  @RequestParam(value = "role", required = false) int role,
                                                  @RequestParam(value = "avatar", required = false) MultipartFile file) {
        String account = request.getParameter("account");
        if (file != null) {
            // 这里传入 account 和 简历头像
            if (pictureService.isExist(account, role, "avatar")) {
                pictureService.deleteAccessory(account, role, "avatar");
            }
            Picture picture = new Picture(account, role);
            picture.setAttributes(file);
            pictureService.saveAccessory(picture, "avatar");
        }

        return new HashMap<>();
    }
}
