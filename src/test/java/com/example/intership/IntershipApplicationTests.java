package com.example.intership;

import com.example.intership.dao.JobTemplate;
import com.example.intership.dao.UserTemplate;
import com.example.intership.entities.user.Applicant;
import com.example.intership.entities.form.Form;
import com.example.intership.entities.job.Job;
import com.example.intership.entities.form.singleform.JobForm;
import com.example.intership.entities.user.Enterprise;
import com.example.intership.entities.user.Student;
import com.example.intership.service.JobService;
import com.example.intership.service.ResumeService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

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
