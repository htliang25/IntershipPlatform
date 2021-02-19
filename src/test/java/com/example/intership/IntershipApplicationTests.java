package com.example.intership;

import com.example.intership.controller.PreviewResumeController;
import com.example.intership.dao.ResumeTemplate;
import com.example.intership.entities.Accessory;
import com.example.intership.service.ResumeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IntershipApplicationTests {
    @Autowired
    ResumeTemplate resumeTemplate;
    @Autowired
    ResumeService resumeService;

    @Test
    void contextLoads(){
//        resumeTemplate.deleteInfo("aa", "informationForm");
//        resumeTemplate.deleteInfo("aa", "jobForm");
//        resumeTemplate.deleteInfo("aa", "educationExperience");
//        resumeTemplate.deleteInfo("aa", "schoolExperience");
//        resumeTemplate.deleteInfo("aa", "projectExperience");
//        resumeTemplate.deleteInfo("aa", "awardExperience");
//        resumeTemplate.deleteInfo("aa", "abilityContent");
//        resumeTemplate.deleteInfo("aa", "evaluationContent");
//        resumeTemplate.deleteInfo("aa", "paperContent");
        Accessory accessory = resumeService.getAvatar("aa");
        System.out.println("test");
    }
}
