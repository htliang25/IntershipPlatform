package com.example.intership;

import com.example.intership.dao.JobTemplate;
import com.example.intership.dao.UserTemplate;
import com.example.intership.entities.Job;
import com.example.intership.entities.User;
import com.example.intership.entities.user.Enterprise;
import com.example.intership.entities.user.Student;
import com.example.intership.service.JobService;
import com.example.intership.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class IntershipApplicationTests {

    @Autowired
    UserTemplate userTemplate;

    @Autowired
    JobTemplate jobTemplate;



    @Test
    void contextLoads(){
//        for (int i = 1;i <= 100;i++) {
//            Student user = new Student();
//            user.setRole(1);
//            user.setAccount("student"+ i);
//            user.setPwd("password" + i);
//            user.setUniversity("university" + i);
//            user.setMajor("major" + i);
//            userTemplate.saveUser(user);
//        }


        String []companyArr = new String[]{"字节跳动", "腾讯", "阿里巴巴", "美团", "快手", "拼多多", "滴滴", "斗鱼", "百度", "虎牙", "华为", "深信服", "哔哩哔哩", "微软", "Google"};
        String []cityArr = new String[]{"北京", "上海", "深圳", "杭州", "南京", "成都", "厦门", "武汉", "其他"};
        String []typeArr = new String[]{"研发", "测试", "数据", "算法", "前端", "产品", "运营", "其他"};
        String []companyTypeArr = new String[]{"互联网", "游戏", "咨询", "直播", "应用服务", "硬件设施", "算法", "视频", "搜索"};
        String []companyAddressArr = new String[]{"北京", "上海", "深圳", "杭州", "南京", "成都", "厦门", "武汉"};

        for (int i = 0;i < companyArr.length;i++) {
            Enterprise user = new Enterprise();
            user.setRole(2);
            user.setAccount(companyArr[i]);
            user.setPwd("123456");
            user.setCompanyName(companyArr[i]);
            user.setCompanyIntro("companyIntro: " + companyArr[i]);
            user.setCompanyType(companyTypeArr[(int)(Math.random()*9)]);
            user.setCompanyAddress(companyAddressArr[(int)(Math.random()*8)]);
            userTemplate.saveUser(user);
        }


        for (int j = 1;j < 250;j++) {
            int companyNumber = (int)(Math.random()*companyArr.length);
            Job job = new Job(companyArr[companyNumber], companyArr[companyNumber]);
            int typeNumber = (int)(Math.random()*8);
            job.setJobCity(cityArr[(int)(Math.random()*9)]);
            job.setJobType(typeArr[typeNumber]);
            job.setJobDuty("职责: 1.认真 2.负责");
            job.setJobDescription("描述: 1.认真 2.负责");
            job.setJobName(typeArr[typeNumber]+ "工程师");
            job.setJobRequire("要求：1.认真 2.负责");
            jobTemplate.publishJob(job);
        }
        for (int j = 1;j < 250;j++) {
            int companyNumber = (int)(Math.random()*companyArr.length);
            Job job = new Job(companyArr[companyNumber], companyArr[companyNumber]);
            int typeNumber = (int)(Math.random()*8);
            job.setJobCity(cityArr[(int)(Math.random()*9)]);
            job.setJobType(typeArr[typeNumber]);
            job.setJobDuty("职责: 1.认真 2.负责");
            job.setJobDescription("描述: 1.认真 2.负责");
            job.setJobName(typeArr[typeNumber]+ "实习生");
            job.setJobRequire("要求: 1.认真 2.负责");
            jobTemplate.publishJob(job);
        }


//
//            Enterprise user = new Enterprise();
//            user.setRole(2);
//            user.setAccount("ByteDance");
//            user.setPwd("123456");
//            user.setCompanyName("字节跳动");
//            user.setCompanyIntro("今日头条、抖音的母公司");
//            userTemplate.saveUser(user);

//        Student user1 = new Student();
//        user1.setRole(1);
//        user1.setAccount("LMS");
//        user1.setPwd("123456");
//        user1.setUniversity("华南师范大学");
//        user1.setMajor("计算机专业");
//        userTemplate.saveUser(user1);


    }
}
