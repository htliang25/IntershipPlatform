package com.example.intership.service;

import com.example.intership.dao.StudentTemplate;
import com.example.intership.entities.user.Student;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentTemplate studentTemplate;

    public List<Student> getStudentList() {
        return studentTemplate.getStudentList();
    }

    public boolean addJob(String account, ObjectId jobId) {
        return studentTemplate.addJob(account, jobId);
    }

    public void deleteJob(ObjectId jobId) {
        studentTemplate.deleteJob(jobId);
    }
}
