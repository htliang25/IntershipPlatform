package com.example.intership.entities.content;

import com.example.intership.entities.Content;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Update;

@Document(collection = "evaluationContent")
public class EvaluationContent extends Content {
    private String content;

    public EvaluationContent(String account) {
        super(account);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static Update modifyContent(String data) {
        Update update = new Update();

        update.set("content", data);

        return update;
    }

    public String getContent() {
        return content;
    }
}
