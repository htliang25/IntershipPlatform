package com.example.intership.service;

import com.example.intership.dao.MessageTemplate;
import com.example.intership.entities.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageTemplate messageTemplate;

    public void saveMessage(Message message) {
        messageTemplate.saveMessage(message);
    }

    public Message getMessage(String jobId, String applicantAccount) {
        return messageTemplate.getMessage(jobId, applicantAccount);
    }

    public List<Message> getMyNotifyMessageList(String applicantAccount) {
        return messageTemplate.getMyNotifyMessageList(applicantAccount);
    }

}
