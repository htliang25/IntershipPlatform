package com.example.intership.entities.discussion;

public class Comment {
    /*
        评论区评论类
        用于保存评论区话题的评论
        类属性有评论发布时间commentTime、评论者commenter和评论内容content
        类函数有类属性的set、get函数
     */
    private String commentTime; // 发布时间
    private String commenter; // 评论者
    private String content; // 评论的内容

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
