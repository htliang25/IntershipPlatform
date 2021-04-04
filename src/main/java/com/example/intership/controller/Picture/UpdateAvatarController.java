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
import java.util.HashMap;
import java.util.Map;

@Controller
public class UpdateAvatarController {
    @Autowired
    PictureService pictureService;

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
                pictureService.deletePicture(account, role, "avatar");
            }
            Picture picture = new Picture(account, role);
            picture.setAttributes(file);
            pictureService.savePicture(picture, "avatar");
        }

        return new HashMap<>();
    }

    //更新个人和企业头像
    public void updateAvatar(String account, int role, MultipartFile file, String colName) {
        if (pictureService.isExist(account, role, colName)) {
            pictureService.deletePicture(account, role, colName);
        }
        Picture avatar = new Picture(account, role);
        avatar.setAttributes(file);
        pictureService.savePicture(avatar, colName);
    }
}
