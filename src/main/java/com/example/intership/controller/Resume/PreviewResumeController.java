package com.example.intership.controller.Resume;

import com.example.intership.entities.resume.Accessory;
import com.example.intership.service.AccessoryService;
import com.example.intership.service.JobService;
import com.example.intership.service.PictureService;
import com.example.intership.service.ResumeService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class PreviewResumeController {
    @Autowired
    ResumeService resumeService;

    @Autowired
    AccessoryService accessoryService;

    @Autowired
    JobService jobService;

    @Autowired
    PictureService pictureService;

    @ResponseBody
    @GetMapping(value = "/UserGetResume")
    public Map<String, Object> PreviewResume(@RequestParam(value = "account", required = false) String account) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        Map<String, Object> informationForm = resumeService.getSingleForm(account, "informationForm");
        data.put("informationForm", informationForm);

        Map<String, Object> jobForm = resumeService.getSingleForm(account, "jobForm");
        data.put("jobForm", jobForm);

        String abilityContent = resumeService.getContent(account, "abilityContent");
        data.put("abilityContent", abilityContent);

        String evaluationContent = resumeService.getContent(account, "evaluationContent");
        data.put("evaluationContent", evaluationContent);

        String paperContent = resumeService.getContent(account, "paperContent");
        data.put("paperContent", paperContent);

        ArrayList educationExperienceList = resumeService.getMultipleForm(account, "educationExperience");
        data.put("educationExperience", educationExperienceList);

        ArrayList schoolExperienceList = resumeService.getMultipleForm(account, "schoolExperience");
        data.put("schoolExperience", schoolExperienceList);

        ArrayList projectExperienceList = resumeService.getMultipleForm(account, "projectExperience");
        data.put("projectExperience", projectExperienceList);

        ArrayList awardExperienceList = resumeService.getMultipleForm(account, "awardExperience");
        data.put("awardExperience", awardExperienceList);

        boolean flag = pictureService.avatarIsExist(account, 1);
        if (flag) {
            ObjectId avatarId = pictureService.getAvatarId(account, 1);
            data.put("avatarURL", "/api/avatar/" + avatarId);
        }

        List<Accessory> accessoryList = accessoryService.getMyAccessoryList(account);
        ArrayList finalAccessoryList = new ArrayList();
        for (Accessory accessory : accessoryList) {
            HashMap<String, Object> accessoryMsg = new HashMap<>();
            accessoryMsg.put("name", accessory.getName());
            accessoryMsg.put("url", accessory.getUrl());
            accessoryMsg.put("contentType", accessory.getContentType());

            accessoryMsg.put("accessoryId", accessory.getId().toString());

            if (accessory.getContentType().equals(".pdf")) {
                accessoryMsg.put("downloadURL", "http://localhost:8089/accessory/pdf/" + account + '/' + accessory.getName());
            } else {
                accessoryMsg.put("downloadURL", "http://localhost:8089/accessory/img/" + account + '/' + accessory.getName());
            }

            finalAccessoryList.add(accessoryMsg);
        }

        data.put("accessoryList", finalAccessoryList);


        map.put("data", data);
        map.put("code", 20001);

        return map;
    }

    @ResponseBody
    @GetMapping(value = "/getResumeName")
    public Map<String, Object> PreviewResumeName(@RequestParam(value = "account", required = false) String account) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        Map<String, Object> form = resumeService.getSingleForm(account, "informationForm");
        String resumeName = (String) form.get("resumeName");
        data.put("resumeName", resumeName);


        map.put("data", data);
        map.put("code", 20001);

        return map;
    }

    @ResponseBody
    @GetMapping(value = "/getApplicants")
    public Map<String, Object> getApplicants(@RequestParam(value = "jobId", required = false) String jobId) {
        ObjectId id = new ObjectId(jobId);

        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        ArrayList list = jobService.getApplicants(id);
        data.put("applicants", list);

        map.put("data", data);
        map.put("code", 20001);

        return map;
    }

}
