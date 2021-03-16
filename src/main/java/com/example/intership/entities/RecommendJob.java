package com.example.intership.entities;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "recommendJob")
public class RecommendJob {
    private ObjectId _id;
    private String account;
    private ArrayList<Job> recommendJobList;

    public RecommendJob () {
        _id = new ObjectId();
        recommendJobList = new ArrayList<>();
    }

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId id) {
        this._id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public ArrayList<Job> getRecommendJobList() {
        return recommendJobList;
    }

    public void setRecommendJobList(ArrayList<Job> recommendJobList) {
        this.recommendJobList = recommendJobList;
    }

}
