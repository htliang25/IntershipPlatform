package com.example.intership;

import com.example.intership.dao.ResumeTemplate;
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

    }
}
