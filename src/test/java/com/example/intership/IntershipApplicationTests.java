package com.example.intership;

import com.example.intership.dao.JobTemplate;
import com.example.intership.dao.UserTemplate;
import com.example.intership.service.JobService;
import com.example.intership.service.ResumeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IntershipApplicationTests {

    @Autowired
    UserTemplate userTemplate;

    @Autowired
    JobTemplate jobTemplate;

    @Autowired
    JobService jobService;

    @Autowired
    ResumeService resumeService;

    @Test
    void contextLoads(){


    }

}
