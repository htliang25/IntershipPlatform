package com.example.intership.dao;


import com.example.intership.entities.Article;
import com.example.intership.entities.Comment;
import com.example.intership.entities.Job;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    public void publishArticle(Article article) {
        mongoTemplate.save(article);
    }

    public Article getArticleById(ObjectId id) {
        Criteria criteria = Criteria.where("_id").is(id);
        Query query = new Query(criteria);

        return mongoTemplate.findOne(query, Article.class);
    }

    public List<Article> getMyArticleList(String account) {
        Criteria criteria = Criteria.where("author").is(account);
        Query query = new Query();

        return mongoTemplate.find(query, Article.class);
    }

    public List<Article> searchArticle(String searchKey) {
        Criteria criteria = new Criteria();

        if (searchKey != null) {
            String title = ".*?" + searchKey + ".*";
            criteria.and("title").regex(title);
        }

        Query query = new Query(criteria);

        return mongoTemplate.find(query, Article.class);
    }

    public void addArticleComment(ObjectId id, Comment comment) {
        Article article = getArticleById(id);
        if (article != null) {
            ArrayList<Comment> commentList = article.getCommentList();
            commentList.add(comment);

            Criteria criteria = Criteria.where("_id").is(id);
            Query query = new Query(criteria);
            Update update = new Update();
            update.set("commentList", commentList);
            mongoTemplate.updateMulti(query, update, "article");
        }
    }

    public void reduceArticleLikeNum (ObjectId id, String account) {
        Article article = getArticleById(id);
        if (article != null) {
            ArrayList<String> likeList = article.getLikeList();
            if (likeList.contains(account)) {
                likeList.remove(account);
            }
            Criteria criteria = Criteria.where("_id").is(id);
            Query query = new Query(criteria);
            Update update = new Update();
            update.set("likeList", likeList);
            mongoTemplate.updateMulti(query, update, "article");
        }
    }

    public void addArticleLikeNum (ObjectId id, String account) {
        Article article = getArticleById(id);
        if (article != null) {
            ArrayList<String> likeList = article.getLikeList();
            if (!likeList.contains(account)) {
                likeList.add(account);
            }
            Criteria criteria = Criteria.where("_id").is(id);
            Query query = new Query(criteria);
            Update update = new Update();
            update.set("likeList", likeList);
            mongoTemplate.updateMulti(query, update, "article");
        }
    }

}
