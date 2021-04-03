package com.example.intership.entities.discussion;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "article")
public class Article {
    /*
        评论区话题类
        用于保存评论区话题
        类属性有id、发布者author、标题title、内容content、发布时间publishTime、文章点赞likeList和文章评论commentList
        类函数有类属性的set、get函数
     */
    private ObjectId id;
    private String author;
    private String title;
    private String content;
    private String publishTime;
    private ArrayList<String> likeList; // 文章的点赞列表
    private ArrayList<Comment> commentList;  // 文章的评论列表

    public Article () {
        id = new ObjectId();
        likeList = new ArrayList<>();
        commentList = new ArrayList<>();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public ArrayList<String> getLikeList() {
        return likeList;
    }

    public void setLikeList(ArrayList<String> likeList) {
        this.likeList = likeList;
    }

    public ArrayList<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(ArrayList<Comment> commentList) {
        this.commentList = commentList;
    }
}
