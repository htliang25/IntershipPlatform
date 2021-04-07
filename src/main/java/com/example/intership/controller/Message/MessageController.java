package com.example.intership.controller.Message;

import com.example.intership.entities.job.Job;
import com.example.intership.entities.message.Message;
import com.example.intership.service.JobService;
import com.example.intership.service.MessageService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    JobService jobService;

    /*
    企业回复学生投递的信息
    api为sendMessageToStudent
    参数为学生id、企业id、消息msg
    返回值为发送消息的结果
 */
    @ResponseBody
    @PostMapping("/sendMessageToStudent")
    public Map<String, Object> sendMessageToStudent(@RequestBody Map<String, Object> data) {
        Map<String, Object> map = new HashMap<>();
        String applicantAccount = (String) data.get("applicantAccount");
        String jobId = (String) data.get("jobId");
        String replyMsg = (String) data.get("replyMsg");

        Message message = new Message();
        message.setReplyMsg(replyMsg);
        message.setJobId(jobId);
        message.setApplicantAccount(applicantAccount);

        messageService.saveMessage(message);

        map.put("code", 20001);
        return map;
    }

    @ResponseBody
    @GetMapping("/getMyNotifyMessage")
    public Map<String, Object> getMyNotifyMessage(@RequestParam(value = "account") String account) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        List<Message> messageList = messageService.getMyNotifyMessageList(account);
        ArrayList readList = new ArrayList();
        ArrayList unreadList = new ArrayList();

        for (int i = messageList.size() - 1; i >= 0; i--) {
            Message message = messageList.get(i);
            HashMap<String, Object> messageMsg = new HashMap<>();
            messageMsg.put("replyMsg", message.getReplyMsg());
            Job job = jobService.getJob(new ObjectId(message.getJobId()));
            if (job != null) {
                messageMsg.put("jobName", job.getJobName());
                messageMsg.put("companyName", job.getCompanyName());
            }
            if (message.isRead()) {
                readList.add(messageMsg);
            } else {
                unreadList.add(messageMsg);
            }
        }

        data.put("readMessageList", readList);
        data.put("unreadMessageList", unreadList);
        map.put("data", data);
        map.put("code", 20001);

        return map;
    }

}
