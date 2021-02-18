package com.example.intership.entities.singleform;

import com.example.intership.entities.Form;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Update;

import java.util.HashMap;
import java.util.Map;

@Document(collection = "abilityContent")
public class AbilityContent extends Form {
    private String content;

    public AbilityContent(String account) {
        super(account);
    }

    public void setAttributes(Map<String, Object> data) {
        setContent((String) data.get("content"));
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static Update modify(Map<String, Object> data) {
        Update update = new Update();

        update.set("content", (String) data.get("content"));

        return update;
    }

    public Map<String, Object> getForm() {
        Map<String, Object> data = new HashMap<>();

        data.put("content", content);

        return data;
    }

    public String getContent() {
        return content;
    }
}
