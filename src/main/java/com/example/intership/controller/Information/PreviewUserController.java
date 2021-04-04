package com.example.intership.controller.Information;

import com.example.intership.entities.user.Enterprise;
import com.example.intership.entities.user.Student;
import com.example.intership.service.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class PreviewUserController {
    @Autowired
    UserService userService;

    @Autowired
    EnterpriseService enterpriseService;

    @Autowired
    JobService jobService;

    @Autowired
    ResumeService resumeService;

    @Autowired
    PictureService pictureService;

    /*
        获取用户（或企业）函数
        api为getUserInfo和getEnterpriseInfo
        参数为用户帐号account和用户角色role
        返回值为用户的个人信息、头像和状态码
     */
    @ResponseBody
    @GetMapping(value = {"/getUserInfo", "/getEnterpriseInfo"})
    public Map<String, Object> getUser(@RequestParam(value = "account", required = false) String account,
                                       @RequestParam(value = "role", required = false) int role) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        if (role == 1) {
            Student student = (Student) userService.getUser(account, role);
            ObjectId avatarId = pictureService.getAvatarId(account, 1);

            if (student != null) {
                data.put("university", student.getUniversity());
                data.put("major", student.getMajor());

                Map<String, Object> infoForm = resumeService.getSingleForm(account, "informationForm");
                data.put("infoForm", infoForm);

                data.put("avatarURL", "/api/avatar/" + avatarId);
                map.put("code", 20001);
                map.put("data", data);
            } else {
                //用户不存在
                map.put("code", 50001);
            }
        } else {
            Enterprise enterprise = (Enterprise) userService.getUser(account, role);
            ObjectId avatarId = pictureService.getAvatarId(account, 2);

            if (enterprise != null) {
                data.put("companyName", enterprise.getCompanyName());
                data.put("companyIntro", enterprise.getCompanyIntro());
                data.put("companyLogoURL", "/api/avatar/" + avatarId);
                data.put("companyType", enterprise.getCompanyType());
                data.put("companyAddress", enterprise.getCompanyAddress());
                data.put("companyJobCount", jobService.getJobNum(account));
                map.put("code", 20001);
                map.put("data", data);
            } else {
                //用户不存在
                map.put("code", 50001);
            }
        }

        return map;
    }

    /*
        获取热门企业函数
        api为getHotCompanyList
        返回长度不大于12的企业列表和状态码
     */
    @ResponseBody
    @GetMapping("/getHotCompanyList")
    public Map<String, Object> getHotCompanyList() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        List<Enterprise> list = enterpriseService.getEnterpriseList();
        ArrayList hotCompanyList = new ArrayList();
        int i = 0;
        for (Enterprise enterprise : list) {
            i++;
            if (i > 12) {
                break;
            }
            HashMap<String, Object> enterpriseMsg = new HashMap<>();
            ObjectId pictureId = pictureService.getAvatarId(enterprise.getAccount(), 2);
            enterpriseMsg.put("logoURL", "/api/avatar/" + pictureId);
            enterpriseMsg.put("companyName", enterprise.getCompanyName());
            enterpriseMsg.put("companyAccount", enterprise.getAccount());
            hotCompanyList.add(enterpriseMsg);
        }
        data.put("hotCompanyList", hotCompanyList);
        map.put("data", data);
        map.put("code", 20001);

        return map;
    }

}
