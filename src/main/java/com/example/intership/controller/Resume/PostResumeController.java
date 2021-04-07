package com.example.intership.controller.Resume;

import com.example.intership.entities.job.Applicant;
import com.example.intership.entities.job.Job;
import com.example.intership.entities.user.Enterprise;
import com.example.intership.entities.user.Student;
import com.example.intership.controller.utils.RecommendUtils;
import com.example.intership.service.JobService;
import com.example.intership.service.StudentService;
import com.example.intership.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PostResumeController {
    @Autowired
    JobService jobService;

    @Autowired
    UserService userService;

    @Autowired
    StudentService studentService;

    /*
        学生投递简历函数
        参数为学生帐号account、入职时间entryTime和所投递工作的id
        返回值为状态码
     */
    @ResponseBody
    @PostMapping("/postResume")
    public Map<String, Object> postResume(@RequestBody Map<String, Object> data) {
        String account = (String) data.get("account");
        String entryTime = (String) data.get("entryTime");
        ObjectId jobId = new ObjectId((String) data.get("id"));

        Map<String, Object> result = new HashMap<>();

        Applicant applicant = new Applicant();
        applicant.setApplicantAccount(account);
        applicant.setEntryTime(entryTime);
        applicant.setJobId((String) data.get("id"));

        boolean flag1 = jobService.addApplicant(jobId, applicant);
        boolean flag2 = studentService.addJob(account, jobId);

        int code = (flag1 && flag2) ? 20001 : 50001;

        ArrayList<Student> studentList = (ArrayList<Student>) studentService.getStudentList();
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

        result.put("code", code);

        return result;
    }

}
