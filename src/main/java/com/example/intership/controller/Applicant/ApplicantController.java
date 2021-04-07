package com.example.intership.controller.Applicant;

import com.example.intership.entities.job.Applicant;
import com.example.intership.entities.job.Job;
import com.example.intership.entities.message.Message;
import com.example.intership.entities.user.Enterprise;
import com.example.intership.entities.user.Student;
import com.example.intership.entities.user.User;
import com.example.intership.service.JobService;
import com.example.intership.service.MessageService;
import com.example.intership.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ApplicantController {

    @Autowired
    UserService userService;

    @Autowired
    JobService jobService;

    @Autowired
    MessageService messageService;

    /*
        获取企业所有已发布工作获得的简历列表函数
        参数为企业帐号account
        返回值为该企业所有已发布工作获得的简历列表
     */
    @ResponseBody
    @GetMapping("/CompanyGetApplicants")
    public Map<String, Object> companyGetApplicants(@RequestParam(value = "account") String account) {
        ArrayList list = new ArrayList();
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> res = new HashMap<>();

        Enterprise enterprise = (Enterprise) userService.getUser(account, 2);
        ArrayList<ObjectId> jobList = enterprise.getJobList();

        for (ObjectId jobId : jobList) {
            Job job = jobService.getJob(jobId);
            ArrayList<Applicant> applicants = job.getApplicants();
            for (Applicant applicant: applicants) {
                HashMap<String, Object> map = new HashMap<>();

                String applicantAccount = applicant.getApplicantAccount();
                Student student = (Student) userService.getUser(applicantAccount, 1);
                map.put("applicantUniversity", student.getUniversity());
                map.put("applicantMajor", student.getMajor());
                map.put("applicantAccount", applicantAccount);
                map.put("entryTime", applicant.getEntryTime());
                map.put("jobId", jobId.toString());
                map.put("jobName", job.getJobName());

                Message message = messageService.getMessage(jobId.toString(), applicantAccount);
                if (message != null) {
                    map.put("replyMsg", message.getReplyMsg());
                }

                list.add(map);
            }
        }

        data.put("applicantList", list);
        data.put("applicantCount", list.size());
        res.put("data", data);
        res.put("code", 20001);
        return res;
    }

    /*
        获取学生已投递简历的工作列表函数
        参数为学生帐号account
        返回值为该学生已投递简历的工作的列表
     */
    @ResponseBody
    @GetMapping("/UserGetApplicants")
    public Map<String, Object> userGetAppliedJobs(@RequestParam(value = "account") String account) {
        ArrayList list = new ArrayList();
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> res = new HashMap<>();

        Student user = (Student) userService.getUser(account, 1);
        ArrayList<ObjectId> jobList = user.getJobList();

        for (ObjectId jobId : jobList) {
            Job job = jobService.getJob(jobId);
            Map<String, Object> map = new HashMap<>();
            map.put("jobId", jobId.toString());
            map.put("jobName", job.getJobName());
            map.put("companyName", job.getCompanyName());
            map.put("jobDesc", job.getJobDescription());
            list.add(map);
        }

        data.put("applicantList", list);
        data.put("applicantCount", list.size());
        res.put("data", data);
        res.put("code", 20001);
        return res;
    }

}
