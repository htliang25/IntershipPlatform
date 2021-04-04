package com.example.intership.controller.Information;

import com.example.intership.entities.user.User;
import com.example.intership.entities.user.Student;
import com.example.intership.controller.utils.RecommendUtils;
import com.example.intership.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ModifyInfoController {
    @Autowired
    UserService userService;

    /*
        修改用户（或企业）信息函数
        api为UserModifyInfo和EnterPriseModifyInfo
        参数为为用户帐号account和用户角色role
        返回值为状态码
     */
    @ResponseBody
    @PostMapping(value = {"/UserModifyInfo", "/EnterPriseModifyInfo"})
    public Map<String, Object> modifyInfo(@RequestBody Map<String, Object> data) {
        String account = (String) data.get("account");
        int role = (int) data.get("role");
        String[] str = new String[4];

        Map map = new HashMap<String, Object>();

        User user = userService.getUser(account, role);

        if (role == 1) {
            str[0] = "student";
            str[1] = (String) data.get("university");
            str[2] = (String) data.get("major");

        } else {
            str[0] = "enterprise";
            str[1] = (String) data.get("companyType");
            str[2] = (String) data.get("companyAddress");
            str[3] = (String) data.get("companyIntro");

        }

        if (user != null) {
            userService.modifyInfo(account, str);

            if (role == 1) {
                ArrayList<Student> studentList = (ArrayList<Student>) userService.getStudentList();
                int index = -1;
                for (int i = 0; i < studentList.size();i++) {
                    Student currentStudent = studentList.get(i);
                    if (currentStudent.getAccount().equals(account)) {
                        index = i;
                        break;
                    }
                }
                if (index != -1) {
                    int finalIndex = index;
                    new Thread () {
                        @Override
                        public void run() {
                            RecommendUtils.recommendJob(finalIndex, studentList);
                        }
                    }.start();
                }
            }

            map.put("code", 20001);
        } else {
            map.put("code", 50001);
        }

        return map;
    }
}
