package com.example.intership.controller;


import com.example.intership.entities.Article;
import com.example.intership.entities.Comment;
import com.example.intership.entities.Job;
import com.example.intership.entities.User;
import com.example.intership.entities.user.Student;
import com.example.intership.service.ArticleService;
import com.example.intership.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    UserService userService;

    @ResponseBody
    @PostMapping("/publishArticle")
    public Map<String, Object> publishArticle(@RequestBody Map<String, Object> data) {
        Map<String, Object> map = new HashMap<>();

        String title = (String) data.get("title");
        String content = (String) data.get("content");
        String author = (String) data.get("account");

        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String publishTime = tempDate.format(new Date());

        Article article = new Article();
        article.setAuthor(author);
        article.setContent(content);
        article.setPublishTime(publishTime);
        article.setTitle(title);

        articleService.publishArticle(article);

        map.put("code", 20001);
        return map;
    }

    @ResponseBody
    @GetMapping("/searchArticle")
    public Map<String, Object> searchArticle(@RequestParam(value = "searchKey", required = false) String searchKey,
                                             @RequestParam(value = "currentPage", required = false) int currentPage,
                                             @RequestParam(value = "pageSize", required = false) int pageSize) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        List<Article> articleList = articleService.searchArticle(searchKey);
        ArrayList  list = new ArrayList();

        int realJobListCount = articleList.size() > (pageSize * currentPage) ? pageSize * currentPage : articleList.size();
        for (int i = (currentPage - 1) * pageSize; i < realJobListCount; i++) {
            HashMap<String, Object> articleMsg = new HashMap<>();
            Article article = articleList.get(i);
            articleMsg.put("title", article.getTitle());
            articleMsg.put("publishTime", article.getPublishTime().substring(0, 10));
            articleMsg.put("articleId", article.getId().toString());
            articleMsg.put("author", article.getAuthor());
            articleMsg.put("likeNum", article.getLikeList().size());
            articleMsg.put("commentNum", article.getCommentList().size());

            list.add(articleMsg);
        }

        data.put("currentArticleList", list);
        data.put("totalArticleCount", articleList.size());

        map.put("data", data);
        map.put("code", 20001);

        return map;
    }


    @ResponseBody
    @PostMapping("/updateArticleLikeNum")
    public Map<String, Object> updateProductLikeNum(@RequestBody Map<String, Object> data) {
        String articleId = (String) data.get("articleId");
        String account = (String) data.get("account");
        Boolean like = (Boolean) data.get("like");

        if (like) {
            articleService.addArticleLikeNum(new ObjectId(articleId), account);
        } else {
            articleService.reduceArticleLikeNum(new ObjectId(articleId), account);
        }

        HashMap map = new HashMap();
        map.put("code", 20001);
        return map;
    }


    @ResponseBody
    @PostMapping("/addArticleComment")
    public Map<String, Object> addArticleComment(@RequestBody Map<String, Object> data) {
        String articleId = (String) data.get("articleId");
        String commenter = (String) data.get("account");
        String content = (String) data.get("content");

        Comment comment = new Comment();
        comment.setCommenter(commenter);
        comment.setContent(content);

        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String commentTime = tempDate.format(new Date());
        comment.setCommentTime(commentTime);

        articleService.addArticleComment(new ObjectId(articleId), comment);

        Map<String, Object> map = new HashMap();
        map.put("code", 20001);

        return map;
    }


    @ResponseBody
    @GetMapping("/getArticleDetail")
    public Map<String, Object> getArticleDetail(@RequestParam(value = "articleId", required = false) String articleId,
                                                @RequestParam(value = "account", required = false) String account) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        Article article = articleService.getArticleById(new ObjectId(articleId));
        if (article != null) {
            data.put("content", article.getContent());
            data.put("author", article.getAuthor());
            data.put("title", article.getTitle());
            data.put("like", article.getLikeList().contains(account));
            data.put("commentList", article.getCommentList());
            data.put("publishTime", article.getPublishTime());

            map.put("data", data);
            map.put("code", 20001);
        }

        return map;
    }

}
