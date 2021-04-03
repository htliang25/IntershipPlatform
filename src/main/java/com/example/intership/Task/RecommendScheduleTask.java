package com.example.intership.Task;

import com.example.intership.entities.user.Student;
import com.example.intership.controller.utils.RecommendUtils;
import com.example.intership.service.JobService;
import com.example.intership.service.RecommendJobService;
import com.example.intership.service.ResumeService;
import com.example.intership.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.*;

@Configuration
@EnableScheduling
public class RecommendScheduleTask {

    @Autowired
    UserService userService;

    @Autowired
    ResumeService resumeService;

    @Autowired
    JobService jobService;

    @Autowired
    RecommendJobService recommendJobService;




    @Scheduled(cron = "* */60 * * * ?")
    private void recommendTask () {
        System.out.println("定时推荐任务 " + LocalDateTime.now());

        // 推荐岗位 不会推送 用户已经投过简历的岗位
        // 遍历 所有的用户， 需要对每一个用户进行推荐 十个工作， 存在另一个表里
        // 遍历 所有的岗位  先通过学生的求职意向 来推荐工作
        // 根据 学生的专业来推荐工作 我先预定一些专业给用户去选择， 然后后台再将这些专业与岗位的type做联系
        // 协同过滤：基于用户 和 基于内容
        // 基于用户：用户小明 投了 A、B岗位  用户小红投了A、B、C岗位 基于用户的协同推荐会把C岗位推荐给A
        // 基于内容：用户小明 投了A岗位， 后台发现B岗位和A岗位投递的人很相似，因此会判定岗位A和岗位B相似， 基于内容的协同过滤会把B岗位推荐给A

        ArrayList<Student> studentList = (ArrayList<Student>) userService.getStudentList();
        for (int i = 0;i < studentList.size();i++) {
            RecommendUtils.recommendJob(i, studentList);
        }

    }

}
