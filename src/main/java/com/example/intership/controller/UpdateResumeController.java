package com.example.intership.controller;

import com.example.intership.entities.Accessory;
import com.example.intership.entities.Content;
import com.example.intership.entities.Form;
import com.example.intership.entities.content.AbilityContent;
import com.example.intership.entities.content.EvaluationContent;
import com.example.intership.entities.content.PaperContent;
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

        Map<String, Object> map = new HashMap<>();
        return map;
    }

    @ResponseBody
    @PostMapping("/UserUpdateResumeAppendix")
    public Map<String, Object> updateResumeAppendix(HttpServletRequest request,
                                                    @RequestParam(value = "appendixList", required = false) ArrayList<MultipartFile> appendixList) {
        String account = (String) request.getParameter("account");
        if (appendixList != null) {
            // 这里传入account 和 附件 可以传多个文件了
            updateAccessory(account, appendixList);
        }
        return new HashMap<>();
    }

    @ResponseBody
    @PostMapping("/UserUpdateResumeAvatar")
    public Map<String, Object> updateResumeAvatar(HttpServletRequest request,
                                                  @RequestParam(value = "avatar", required = false) MultipartFile file) {
        String account = (String) request.getParameter("account");
        if (file != null) {
            // 这里传入account 和 简历头像
            updateAvatar(account, file);
        }

        return new HashMap<>();
    }

    //更新个人基本资料，求职意向
    public void updateSingleForm(String account, Map<String, Object> data, String colName) {
        if (resumeService.isExist(account, colName)) {
            resumeService.modifySingleForm(account, colName, data);
        } else {
            switch (colName) {
                case "informationForm":
                    Form form = new InformationForm(account);
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
            switch (colName) {
                case "abilityContent":
                    Content content = new AbilityContent(account);
                    content.setContent(data);
                    resumeService.saveContent(content, colName);
                    break;
                case "evaluationContent":
                    content = new EvaluationContent(account);
                    content.setContent(data);
                    resumeService.saveContent(content, colName);
                    break;
                case "paperContent":
                    content = new PaperContent(account);
                    content.setContent(data);
                    resumeService.saveContent(content, colName);
                    break;
                default:
                    break;
            }
        }
    }

    //更新个人附件作品
    public void updateAccessory(String account, ArrayList list) {
        int len = list.size();
        for (int i = 0; i < len; i++) {
            Accessory accessory = new Accessory(account);
            accessory.setAttributes((MultipartFile) list.get(i));
            resumeService.saveAccessory(accessory, "accessory");
        }
    }

    public void updateAvatar(String account, MultipartFile file) {
        Accessory accessory = new Accessory(account);
        accessory.setAttributes(file);
        resumeService.saveAccessory(accessory, "avatar");
    }
}
