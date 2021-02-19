package com.example.intership.controller;

import com.example.intership.entities.Accessory;
import com.example.intership.entities.multipleform.AwardExperience;
import com.example.intership.entities.multipleform.EducationExperience;
import com.example.intership.entities.multipleform.ProjectExperience;
import com.example.intership.entities.multipleform.SchoolExperience;
import com.example.intership.entities.singleform.*;
import com.example.intership.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UpdateResumeController {
    @Autowired
    ResumeService resumeService;

    @ResponseBody
    @PostMapping("/UserUpdateResumeInfo")
    public Map<String, Object> updateResume(@RequestBody Map<String, Object> data) {
        String account = (String) data.get("account");

        Map<String, Object> informationForm = (HashMap<String, Object>) data.get("informationForm");
        updateInformationForm(account, informationForm);
        Map<String, Object> jobForm = (HashMap<String, Object>) data.get("jobForm");
        updateJobForm(account, jobForm);
        Map<String, Object> abilityContent = (HashMap<String, Object>) data.get("abilityContent");
        updateAbilityContent(account, abilityContent);
        Map<String, Object> evaluationContent = (HashMap<String, Object>) data.get("evaluationContent");
        updateEvaluationContent(account, evaluationContent);
        Map<String, Object> paperContent = (HashMap<String, Object>) data.get("paperContent");
        updatePaperContent(account, paperContent);

        ArrayList educationExperience = (ArrayList) data.get("educationExperience");
        updateEducationExperience(account, educationExperience);
        ArrayList schoolExperience = (ArrayList) data.get("schoolExperience");
        updateSchoolExperience(account, schoolExperience);
        ArrayList projectExperience = (ArrayList) data.get("projectExperience");
        updateProjectExperience(account, projectExperience);
        ArrayList awardExperience = (ArrayList) data.get("awardExperience");
        updateAwardExperience(account, awardExperience);

        Map<String, Object> map = new HashMap<>();
        return map;
    }

    //更新个人基本资料
    public void updateInformationForm(String account, Map<String, Object> data) {
        InformationForm informationForm = new InformationForm(account);
        informationForm.setAttributes(data);

        resumeService.saveForm(informationForm, "informationForm");
    }

    //更新个人教育经历
    public void updateEducationExperience(String account, ArrayList list) {
        int len = list.size();
        for (int i = 0; i < len; i++) {
            EducationExperience educationExperience = new EducationExperience(account);
            educationExperience.setAttributes((Map<String, Object>) list.get(i));
            resumeService.saveForm(educationExperience, "educationExperience");
        }
    }

    //更新个人校内经历
    public void updateSchoolExperience(String account, ArrayList list) {
        int len = list.size();
        for (int i = 0; i < len; i++) {
            SchoolExperience schoolExperience = new SchoolExperience(account);
            schoolExperience.setAttributes((Map<String, Object>) list.get(i));
            resumeService.saveForm(schoolExperience, "schoolExperience");
        }
    }

    //更新个人求职意向
    public void updateJobForm(String account, Map<String, Object> data) {
        JobForm jobForm = new JobForm(account);
        jobForm.setAttributes(data);

        resumeService.saveForm(jobForm, "jobForm");
    }

    //更新个人项目经历
    public void updateProjectExperience(String account, ArrayList list) {
        int len = list.size();
        for (int i = 0; i < len; i++) {
            ProjectExperience projectExperience = new ProjectExperience(account);
            projectExperience.setAttributes((Map<String, Object>) list.get(i));
            resumeService.saveForm(projectExperience, "projectExperience");
        }
    }

    //更新个人获奖情况
    public void updateAwardExperience(String account, ArrayList list) {
        int len = list.size();
        for (int i = 0; i < len; i++) {
            AwardExperience awardExperience = new AwardExperience(account);
            awardExperience.setAttributes((Map<String, Object>) list.get(i));
            resumeService.saveForm(awardExperience, "awardExperience");
        }
    }

    //更新个人个人技能
    public void updateAbilityContent(String account, Map<String, Object> data) {
        AbilityContent abilityContent = new AbilityContent(account);
        abilityContent.setAttributes(data);

        resumeService.saveForm(abilityContent, "abilityContent");
    }

    //更新个人自我评价
    public void updateEvaluationContent(String account, Map<String, Object> data) {
        EvaluationContent evaluationContent = new EvaluationContent(account);
        evaluationContent.setAttributes(data);

        resumeService.saveForm(evaluationContent, "evaluationContent");
    }

    //更新个人论文发表
    public void updatePaperContent(String account, Map<String, Object> data) {
        PaperContent paperContent = new PaperContent(account);
        paperContent.setAttributes(data);

        resumeService.saveForm(paperContent, "paperContent");
    }

    //更新个人附件作品
    public void updateAccessory(String account, ArrayList list) {
        int len = list.size();
        for(int i = 0; i < len; i++) {
            Accessory accessory = new Accessory(account);
            accessory.setAttributes((MultipartFile) list.get(i));
            resumeService.saveAccessory(accessory);
        }
    }

    @ResponseBody
    @PostMapping("/UserUpdateResumeAppendix")
    public Map<String, Object>updateResumeAppendix(HttpServletRequest request, @RequestParam(value = "appendixList", required = false) ArrayList<MultipartFile> appendixList) {
        String account = (String) request.getParameter("account");

        // 这里传入account 和 附件 可以传多个文件了
        return new HashMap<>();
    }

    @ResponseBody
    @PostMapping("/UserUpdateResumeAvatar")
    public Map<String, Object>updateResumeAvatar(HttpServletRequest request, @RequestParam(value = "avatar", required = false) MultipartFile file) {
        String account = (String) request.getParameter("account");

        // 这里传入account 和 简历头像
        return new HashMap<>();
    }

}
