package com.example.intership.controller;

import com.example.intership.entities.Accessory;
import com.example.intership.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @GetMapping(value = "/avatar/{account}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public byte[] PreviewAvatar(@PathVariable String account) {
        Accessory accessory = resumeService.getAvatar(account);

        return (accessory != null) ? accessory.getContent() : new byte[0];
    }
}
