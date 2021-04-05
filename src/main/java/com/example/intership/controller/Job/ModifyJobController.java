package com.example.intership.controller.Job;

import com.example.intership.service.EnterpriseService;
import com.example.intership.service.JobService;
import com.example.intership.service.StudentService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ModifyJobController {
    @Autowired
    JobService jobService;

    @Autowired
    EnterpriseService enterpriseService;

    @Autowired
    StudentService studentService;

    /*
        更新工作函数
        api为CompanyUpdateJob
        参数为工作jobId和修改内容data
        返回值为状态码
     */
    @ResponseBody
    @PostMapping(value = "CompanyUpdateJob")
    public Map<String, Object> updateJob(@RequestBody Map<String, Object> data) {
        ObjectId id = new ObjectId((String) data.get("jobId"));

        jobService.updateJob(id, data);

        Map<String, Object> res = new HashMap<>();
        res.put("code", 20001);

        return res;
    }

    /*
        删除工作函数
        api为CompanyDeleteJob
        参数为工作jobId
        返回值为状态码
     */
    @ResponseBody
    @PostMapping(value = "CompanyDeleteJob")
    public Map<String, Object> deleteJob(@RequestBody Map<String, Object> data) {
        ObjectId id = new ObjectId((String) data.get("jobId"));

        studentService.deleteJob(id);
        enterpriseService.deleteJob(id);
        jobService.deleteJob(id);

        Map<String, Object> res = new HashMap<>();
        res.put("code", 20001);

        return res;
    }
}
