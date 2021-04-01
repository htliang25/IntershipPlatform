package com.example.intership.service;

import com.example.intership.dao.ArticleTemplate;
import com.example.intership.entities.Article;
import com.example.intership.entities.Comment;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleTemplate articleTemplate;

    public void publishArticle(Article article) {
        articleTemplate.publishArticle(article);
    }

    public Article getArticleById(ObjectId id) {
        return articleTemplate.getArticleById(id);
    }

    public List<Article> getMyArticleList(String account) {
        return articleTemplate.getMyArticleList(account);
    }

    public List<Article> searchArticle(String searchKey) {
        return articleTemplate.searchArticle(searchKey);
    }

    public void addArticleComment(ObjectId id, Comment comment) {
        articleTemplate.addArticleComment(id, comment);
    }

    public void reduceArticleLikeNum (ObjectId id, String account) {
        articleTemplate.reduceArticleLikeNum(id, account);
    }

    public void addArticleLikeNum (ObjectId id, String account) {
        articleTemplate.addArticleLikeNum(id, account);
    }

}
