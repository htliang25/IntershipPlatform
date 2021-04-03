package com.example.intership.dao;

import com.example.intership.entities.user.User;
import com.example.intership.entities.user.Enterprise;
import com.example.intership.entities.user.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserTemplate {
    @Autowired
    MongoTemplate mongoTemplate;

    /*
        获取用户函数
        参数为账户account和角色role
        返回值为符合条件的用户或空值
     */
    public User getUser(String account, int role) {
        Criteria criteria = Criteria.where("account").is(account);
        Query query = new Query(criteria);

        if (role == 1) {
            return mongoTemplate.findOne(query, Student.class);
        } else {
            return mongoTemplate.findOne(query, Enterprise.class);
        }
    }

    /*
        获取全部学生函数
        返回值为学生表中的全部用户
     */
    public List<Student> getStudentList () {
        return mongoTemplate.findAll(Student.class);
    }

    /*
        获取全部企业函数
        返回值为企业表中的全部用户
     */
    public List<Enterprise> getEnterpriseList () {
        return mongoTemplate.findAll(Enterprise.class);
    }

    /*
        保存用户函数
        参数为一个用户
     */
    public void saveUser(User user) {
        mongoTemplate.save(user);
    }

    /*
        修改密码函数
        参数为用户账号account、密码pwd和用户所在表col_name
     */
    public void modifyPwd(String account, String pwd, String col_name) {
        Criteria criteria = Criteria.where("account").is(account);
        Query query = new Query(criteria);

        Update update = new Update();
        update.set("pwd", pwd);

        mongoTemplate.updateMulti(query, update, col_name);
    }

    /*
        修改用户信息函数
        参数为用户账号account和需要修改属性名str[]
     */
    public void modifyInfo(String account, String[] str) {
        Criteria criteria = Criteria.where("account").is(account);
        Query query = new Query(criteria);

        Update update = new Update();

        if (str[0].equals("student")) {
            update.set("university", str[1]);
            update.set("major", str[2]);
        } else {
            update.set("companyType", str[1]);
            update.set("companyAddress", str[2]);
            update.set("companyIntro", str[3]);
        }

        mongoTemplate.updateMulti(query, update, str[0]);
    }

    /*
        查找企业函数
        参数为搜索关键词
        返回值为字符匹配的公司
     */
    public List<Enterprise> searchEnterprise (String searchKey) {
        String companyName = ".*?" + searchKey + ".*";
        Criteria criteria1 = Criteria.where("companyName").regex(companyName);
        Query query1 = new Query(criteria1);

        return mongoTemplate.find(query1, Enterprise.class);
    }
}
