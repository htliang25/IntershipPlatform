package com.example.intership.controller;

import com.example.intership.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PreviewResumeController {
    @Autowired
    ResumeService resumeService;

    @ResponseBody
    @GetMapping(value = "/UserGetResume")
    public Map<String, Object> PreviewResume(@RequestParam(value = "account", required = false) String account) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        Map<String, Object> form = resumeService.getSingleForm(account, "informationForm");
        data.put("informationForm", form);

        form = resumeService.getSingleForm(account, "jobForm");
        data.put("jobForm", form);

        String content = resumeService.getContent(account, "abilityContent");
        data.put("abilityContent", content);

        content = resumeService.getContent(account, "evaluationContent");
        data.put("evaluationContent", content);

        content = resumeService.getContent(account, "paperContent");
        data.put("paperContent", content);

        ArrayList  list = resumeService.getMultipleForm(account, "educationExperience");
        data.put("educationExperience", list);

        list = resumeService.getMultipleForm(account, "schoolExperience");
        data.put("schoolExperience", list);

        list = resumeService.getMultipleForm(account, "projectExperience");
        data.put("projectExperience", list);

        list = resumeService.getMultipleForm(account, "awardExperience");
        data.put("awardExperience", list);

        String url = "http://localhost:8089/avatar/" + account;
        data.put("avatarURL", url);

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
}
