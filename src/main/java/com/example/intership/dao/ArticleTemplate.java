package com.example.intership.dao;

import com.example.intership.entities.discussion.Article;
import com.example.intership.entities.discussion.Comment;
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

    /*
        发布话题函数
        参数为话题article
     */
    public void publishArticle(Article article) {
        mongoTemplate.save(article);
    }

    /*
        获取话题函数
        参数为话题id
        返回值为对应id的话题
     */
    public Article getArticleById(ObjectId id) {
        Criteria criteria = Criteria.where("_id").is(id);
        Query query = new Query(criteria);

        return mongoTemplate.findOne(query, Article.class);
    }

    /*
        获取用户发表话题函数
        参数为用户帐号account
        返回值为对应用户所发表的话题列表
     */
    public List<Article> getMyArticleList(String account) {
        Criteria criteria = Criteria.where("author").is(account);
        Query query = new Query(criteria);

        return mongoTemplate.find(query, Article.class);
    }

    /*
        搜索话题函数
        参数为搜索关键字searchKey
        返回值为对应的话题
     */
    public List<Article> searchArticle(String searchKey) {
        Criteria criteria = new Criteria();

        if (searchKey != null) {
            String title = ".*?" + searchKey + ".*";
            criteria.and("title").regex(title);
        }

        Query query = new Query(criteria);

        return mongoTemplate.find(query, Article.class);
    }

    /*
        添加话题评论函数
        参数为话题id和评论comment
     */
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

    /*
        删除话题点赞数函数
        参数为话题的id和评论用户帐号account
     */
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

    /*
        增加话题点赞数函数
        参数为话题的id和评论用户帐号account
     */
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
