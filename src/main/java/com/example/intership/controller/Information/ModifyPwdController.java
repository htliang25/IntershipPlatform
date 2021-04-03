package com.example.intership.controller.Information;

import com.example.intership.entities.user.User;
import com.example.intership.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ModifyPwdController {
    @Autowired
    UserService userService;

    /*
        修改密码函数
        api为UserModifyPwd和EnterPriseModifyPwd
        参数为用户账户account、旧密码oldPassword和新密码newPassword
        返回值为状态码
    */
    @ResponseBody
    @PostMapping(value = {"/UserModifyPwd", "/EnterPriseModifyPwd"})
    public Map<String, Object> modifyPwd(@RequestBody Map<String, Object> data) {
        String account = (String) data.get("account");
        String old_pwd = (String) data.get("oldPassword");
        String new_pwd = (String) data.get("newPassword");
        int role = (int) data.get("role");


        Map<String, Object> map = new HashMap<>();

        User user = userService.getUser(account, role);

        String col_name = (role == 1) ? "student" : "enterprise";

        if (user != null && old_pwd.equals(user.getPwd())) {
            userService.modifyPwd(account, new_pwd, col_name);
            map.put("code", 20001);
        } else if (user == null) {
            map.put("code", 50001);
        } else {
            map.put("code", 50002);
        }
        return map;
    }
}
