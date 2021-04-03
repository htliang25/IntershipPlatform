package com.example.intership.controller.LoginAndRegister;

import com.example.intership.entities.user.Enterprise;
import com.example.intership.entities.user.Student;
import com.example.intership.controller.utils.RecommendUtils;
import com.example.intership.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RegisterController {
    @Autowired
    UserService userService;

    /*
        注册函数
        api为register
        参数为用户账号account、用户密码pwd和用户角色role
        返回值为状态码
     */
    @ResponseBody
    @RequestMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, Object> data) {
        String name = (String) data.get("account");
        int role = (int) data.get("role");

        Map map = new HashMap<String, Object>();

        if (role == 1) {
            if (userService.getUser(name, 1) == null) {
                Student student = new Student();
                String account = (String) data.get("account");
                student.setAccount(account);
                student.setPwd((String) data.get("password"));
                student.setUniversity((String) data.get("university"));
                student.setMajor((String) data.get("major"));
                userService.saveUser(student);

                {
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
                // 账号已注册
                map.put("code", 50001);
            }
        } else {
            if (userService.getUser(name, 2) == null) {
                Enterprise enterprise = new Enterprise();
                enterprise.setAccount((String) data.get("account"));
                enterprise.setPwd((String) data.get("password"));
                enterprise.setCompanyName((String) data.get("companyName"));
                enterprise.setCompanyIntro((String) data.get("companyIntro"));
                userService.saveUser(enterprise);
                map.put("code", 20001);
            } else {
                // 账号已注册
                map.put("code", 50001);
            }
        }
        return map;
    }
}
