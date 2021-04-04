package com.example.intership.controller.Resume;

import com.example.intership.entities.resuem.Content;
import com.example.intership.entities.resuem.Form;
import com.example.intership.entities.resuem.multipleform.AwardExperience;
import com.example.intership.entities.resuem.multipleform.EducationExperience;
import com.example.intership.entities.resuem.multipleform.ProjectExperience;
import com.example.intership.entities.resuem.multipleform.SchoolExperience;
import com.example.intership.entities.resuem.singleform.InformationForm;
import com.example.intership.entities.resuem.singleform.JobForm;

import com.example.intership.entities.user.Student;
import com.example.intership.controller.utils.RecommendUtils;
import com.example.intership.service.ResumeService;
import com.example.intership.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UpdateResumeController {
    @Autowired
    ResumeService resumeService;

    @Autowired
    UserService userService;

    //更新个人简历信息
    @ResponseBody
    @PostMapping("/UserUpdateResumeInfo")
    public Map<String, Object> updateResume(@RequestBody Map<String, Object> data) {
        String account = (String) data.get("account");

        Map<String, Object> informationForm = (HashMap<String, Object>) data.get("informationForm");
        informationForm.put("resumeName", (String) data.get("resumeName"));
        updateSingleForm(account, informationForm, "informationForm");

        Map<String, Object> jobForm = (HashMap<String, Object>) data.get("jobForm");
        updateSingleForm(account, jobForm, "jobForm");

        String abilityContent = (String) data.get("abilityContent");
        updateContent(account, abilityContent, "abilityContent");

        String evaluationContent = (String) data.get("evaluationContent");
        updateContent(account, evaluationContent, "evaluationContent");

        String paperContent = (String) data.get("paperContent");
        updateContent(account, paperContent, "paperContent");

        ArrayList educationExperience = (ArrayList) data.get("educationExperience");
        updateMultipleForm(account, educationExperience, "educationExperience");

        ArrayList schoolExperience = (ArrayList) data.get("schoolExperience");
        updateMultipleForm(account, schoolExperience, "schoolExperience");

        ArrayList projectExperience = (ArrayList) data.get("projectExperience");
        updateMultipleForm(account, projectExperience, "projectExperience");

        ArrayList awardExperience = (ArrayList) data.get("awardExperience");
        updateMultipleForm(account, awardExperience, "awardExperience");

        {
            ArrayList<Student> studentList = (ArrayList<Student>) userService.getStudentList();
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
        }


        Map<String, Object> map = new HashMap<>();
        map.put("code", 20001);
        return map;
    }


    //更新个人基本资料，求职意向
    public void updateSingleForm(String account, Map<String, Object> data, String colName) {
        if (resumeService.isExist(account, colName)) {
            resumeService.modifySingleForm(account, colName, data);
        } else {
            Form form;
            switch (colName) {
                case "informationForm":
                    form = new InformationForm(account);
                    form.setAttributes(data);
                    resumeService.saveForm(form, colName);
                    break;
                case "jobForm":
                    form = new JobForm(account);
                    form.setAttributes(data);
                    resumeService.saveForm(form, colName);
                    break;
                default:
                    break;
            }
        }
    }

    //更新个人教育经历，校内经历，项目经历，获奖情况
    public void updateMultipleForm(String account, ArrayList list, String colName) {
        if (resumeService.isExist(account, colName)) {
            resumeService.deleteInfo(account, colName);
        }
        int len = list.size();
        for (int i = 0; i < len; i++) {
            switch (colName) {
                case "educationExperience":
                    Form form = new EducationExperience(account);
                    form.setAttributes((Map<String, Object>) list.get(i));
                    resumeService.saveForm(form, colName);
                    break;
                case "schoolExperience":
                    form = new SchoolExperience(account);
                    form.setAttributes((Map<String, Object>) list.get(i));
                    resumeService.saveForm(form, colName);
                    break;
                case "projectExperience":
                    form = new ProjectExperience(account);
                    form.setAttributes((Map<String, Object>) list.get(i));
                    resumeService.saveForm(form, colName);
                    break;
                case "awardExperience":
                    form = new AwardExperience(account);
                    form.setAttributes((Map<String, Object>) list.get(i));
                    resumeService.saveForm(form, colName);
                    break;
            }
        }
    }

    //更新个人个人技能，自我评价，论文发表
    public void updateContent(String account, String data, String colName) {
        if (resumeService.isExist(account, colName)) {
            resumeService.modifyContent(account, colName, data);
        } else {
            Content content = new Content(account);
            content.setContent(data);
            resumeService.saveContent(content, colName);
        }
    }
}
