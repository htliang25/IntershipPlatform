package com.example.intership;

import com.example.intership.dao.ResumeTemplate;

import com.example.intership.entities.multipleform.AwardExperience;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@SpringBootTest
class IntershipApplicationTests {
    @Autowired
    ResumeTemplate resumeTemplate;

    @Test
    void contextLoads(){
//        ArrayList data = new ArrayList();
//        Map<String, Object> s = new HashMap<>();
//        AwardExperience a = new AwardExperience("aa");
//        s.put("name", "一等奖");
//        s.put("time", "2018-4-25");
//        a.setAttributes(s);
//        data.add(a);
//
//        s = new HashMap<>();
//        a = new AwardExperience("aa");
//        s.put("name", "二等奖");
//        s.put("time", "2019-11-25");
//        a.setAttributes(s);
//        data.add(a);
//        resumeTemplate.saveMultipleForm(data, "awardExperience");

        resumeTemplate.deleteMultipleForm("aa", "awardExperience");
    }
}
