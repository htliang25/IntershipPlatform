package com.example.intership.Task;

import com.example.intership.entities.Applicant;
import com.example.intership.entities.Form;
import com.example.intership.entities.Job;
import com.example.intership.entities.user.Student;
import com.example.intership.service.JobService;
import com.example.intership.service.RecommendJobService;
import com.example.intership.service.ResumeService;
import com.example.intership.service.UserService;
import org.bson.types.ObjectId;
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

    /*
        推荐的工作职位需要参考以下因素
        学生注册时的专业  这个比较复杂  需要去解析输入的专业适合什么岗位，
        学生简历的求职意向  地点：cityOptions: []  岗位: typeOptions: [],
        协同过滤的推荐  基于用户相似度和内容相似度
     */

    static String[]jobTypeArr = new String[]{"测试", "数据分析", "算法", "前端", "后端", "客户端", "游戏开发", "产品经理", "运营", "行政", "人事", "美术设计", "商务合作",  "硬件设施", "法务"};
    static String[]majorArr = new String[]{"管理科学与工程", "工商管理", "公关管理", "电子信息科学", "机械", "经济学", "语言文学", "艺术", "计算机科学与技术", "新闻传播学", "心理学", "数学", "物理学", "法学"};

    static HashMap<String, Integer> jobTypeIndexMap = new HashMap<>();
    static HashMap<String, Integer> majorIndexMap = new HashMap<>();

    // 专业与岗位合适矩阵
    static int[][] suitMap = new int[][] {

            {0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0},  // 测试
            {0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0},  // 数据分析
            {0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0},  // 算法
            {0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0},  // 前端
            {0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0},  // 后端
            {0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0},  // 客户端
            {0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0},  // 游戏开发
            {0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0},  // 产品经理
            {1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1},  // 运营
            {1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0},  // 行政
            {1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0},  // 人事
            {0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0},  // 美术设计
            {0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},  // 商务合作
            {0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0},  // 硬件设施
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},  // 法务

    };

    // 降序排序
    static <K, V extends Comparable<? super V>> List<K> sortByValueDescending(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>()
        {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2)
            {
                int compare = (o1.getValue()).compareTo(o2.getValue());
                return -compare;
            }
        });
        List<K> result = new ArrayList<>();
        for (Map.Entry<K, V> entry : list) {
            result.add(entry.getKey());
        }
        return result;
    }


    private Boolean jobTypeSuitForMajor(String jobType, String major) {
        int jobTypeIndex = jobTypeIndexMap.get(jobType) == null ? -1 : jobTypeIndexMap.get(jobType);;
        int majorIndex = majorIndexMap.get(major) == null ? -1 : majorIndexMap.get(major);
        if (jobTypeIndex == -1) {
            for (int i = 0;i < jobTypeArr.length;i++) {
                if (jobTypeArr[i].equals(jobType)) {
                    jobTypeIndex = i;
                    jobTypeIndexMap.put(jobType, i);
                    break;
                }
            }
        }
        if (majorIndex == -1) {
            for (int i = 0;i < majorArr.length;i++) {
                if (majorArr[i].equals(major)) {
                    majorIndex = i;
                    majorIndexMap.put(major, i);
                    break;
                }
            }
        }
        if (jobTypeIndex == -1 || majorIndex == -1) {
            return false;
        }

        return suitMap[jobTypeIndex][majorIndex] == 1 ? true : false;
    }


    private void updateStudentRecommendList(String account, ArrayList<Job> recommendList) {
        recommendJobService.updateRecommendJobList(account, recommendList);
    }

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

        List<Student> studentList = userService.getStudentList();
        for (int i = 0;i < studentList.size();i++) {

            HashSet<Job> finalSemiJobSet = new HashSet<>();

            Student student = studentList.get(i);
            String account = student.getAccount();

            ArrayList<Applicant> currentStudentApplicantList = student.getApplicants();
            ArrayList<String> currentStudentApplicantJobIdList = new ArrayList<>();
            for (Applicant applicant : currentStudentApplicantList) {
                currentStudentApplicantJobIdList.add(applicant.getJobId());
            }

            List<Job> allJobList = jobService.getJobList("全国", "全部");

            // 学生简历的求职意向 地址和工作
            Map<String, Object> jobForm = resumeService.getSingleForm(account, "jobForm");
            String jobAddress = (String) jobForm.get("address");
            String jobType = (String) jobForm.get("job");

            System.out.println();
            System.out.println(student.getAccount());


            // 一、求职意向
            if (jobAddress != null && jobType != null) {
                List<Job> recommendJobList = jobService.getJobList(jobAddress, jobType);
                System.out.println("-----根据求职意向来推荐-----");

                // 学生简历的求职意向 为第一指标

                int sum = 0;
                for (Job job : recommendJobList) {
                    if (!currentStudentApplicantJobIdList.contains(job.getId().toString())) {
                        finalSemiJobSet.add(job);
                        sum++;
                    }
                }
                System.out.println("基于求职意向 = "  + sum);
            }



            // 二、专业
            String major = student.getMajor();
            if (major != null) {

                System.out.println("-----根据学生的专业来推荐-----");
                System.out.println("学生的专业为 " + major);

                int sum = 0;
                for (Job job : allJobList) {
                    if (!currentStudentApplicantJobIdList.contains(job.getId().toString())) {
                        if (jobTypeSuitForMajor(job.getJobType(), major)) {
                            finalSemiJobSet.add(job);
                            sum++;
                        }
                    }
                }
                System.out.println("基于专业 = " + sum);
            }


            // 三、基于用户协同过滤推荐

            System.out.println("-----根据用户相似度的协同过滤来推荐-----");

            /* 基于用户协同过滤
                1. 用map 记录 (anotherStudent, rate)
                2. 对map排序， 在rate >= 0.6 的anotherStudent里去获取myStudent没有的job
             */

            Map<Student, Double> semiStudentMap = new HashMap<>();

            for (int j = 0;j < studentList.size();j++) {
                // 不和自己做比较
                if (i == j) {
                    continue;
                }
                Student anotherStudent = studentList.get(j);
                ArrayList<Applicant> anotherApplicantList = anotherStudent.getApplicants();

                // 求交集
                double intersection = 0;
                for (Applicant anotherApplicant : anotherApplicantList) {
                    if (currentStudentApplicantJobIdList.contains(anotherApplicant.getJobId())) {
                        intersection++;
                    }
                }

                double union = anotherApplicantList.size() + currentStudentApplicantList.size() - intersection;
                double rate = intersection / union;

                if (rate >= 0.6) {
                    semiStudentMap.put(anotherStudent, rate);
                }
            }

            List<Student> semiStudentList = sortByValueDescending(semiStudentMap);

            int userBasedSum = 0;
            for (Student semiStudent : semiStudentList) {
                ArrayList<Applicant> semiApplicantList = semiStudent.getApplicants();
                for (Applicant semiApplicant : semiApplicantList) {
                    String semiJobId = semiApplicant.getJobId();
                    if (!currentStudentApplicantJobIdList.contains(semiJobId)) {
                        Job job = jobService.getJob(new ObjectId(semiJobId));
                        finalSemiJobSet.add(job);
                        userBasedSum++;
                    }
                }
            }
            System.out.println("基于用户的协同过滤 = " + userBasedSum);



            // 四、基于内容协同过滤推荐

            System.out.println("-----根据内容相似度的协同过滤来推荐-----");

             /*基于内容的协同过滤
            1. 需要先计算出job的相似度矩阵
            2. 相似度矩阵的原理如下：
            先得出job的被投递矩阵
              UserA UserB UserC
            JobX: [1, 1, 1]
            jobY: [1, 0, 1]
            jobZ: [1, 1, 0]

            从而得出semi矩阵
            semiX: [1, 2/3, 2/3]
            semiY: [2/3, 1, 1/3]
            semiZ: [2/3, 1/3, 1]

            因此JobX的TopSemiJob为 jobY、jobZ
            同理jobY的TopSemiJob为 jobX
            而jobZ的TopSemiJob为 JobX

            3. 再根据每个student的applicant.job 去找semiJob, 做一个去重 即可进行推荐

         */

            // 二维矩阵 获取每个job的投递情况
            ArrayList<ArrayList <String> > jobPostAccountArr = new ArrayList<>();
            for (int index1 = 0; index1 < allJobList.size(); index1++) {
                Job job = allJobList.get(index1);
                List<Applicant> applicantList = job.getApplicants();
                ArrayList singleJobPostAccountArr = new ArrayList();

                for (Applicant applicant : applicantList) {
                    singleJobPostAccountArr.add(applicant.getApplicantAccount());
                }

                jobPostAccountArr.add(singleJobPostAccountArr);
            }

            // job的semi矩阵
            Map<String, Map<String, Double> > semiJobMap = new HashMap();
            for (int index1 = 0; index1 < allJobList.size(); index1++) {
                ArrayList<String> currentJobPostAccountArr = jobPostAccountArr.get(index1);
                Job currentJob = allJobList.get(index1);

                HashMap<String, Double> singleJobSemiMap = new HashMap<>();
                for (int index2 = 0; index2 < allJobList.size(); index2++) {
                    if (index1 == index2) {
                        continue;
                    }
                    ArrayList<String> anotherJobPostAccountArr = jobPostAccountArr.get(index2);
                    Job anotherJob = allJobList.get(index2);
                    double intersection = 0;
                    for (String anotherJobAccount : anotherJobPostAccountArr) {
                        if (currentJobPostAccountArr.contains(anotherJobAccount)) {
                            intersection++;
                        }
                    }
                    double union = currentJobPostAccountArr.size() + anotherJobPostAccountArr.size() - intersection;
                    double rate = intersection / union;

                    if (rate >= 0.6) {
                        singleJobSemiMap.put(anotherJob.getId().toString(), rate);
                    }
                }

                semiJobMap.put(currentJob.getId().toString(), singleJobSemiMap);
            }

            // 获取myStudent的applicants 然后去遍历拿到每个job的rate>=0.6的semiJob 去重

            int itemBasedSum = 0;

            for (Applicant applicant : currentStudentApplicantList) {
                Map<String, Double> singleSemiJobMap = semiJobMap.get(applicant.getJobId());
                List<String> singleSemiJobArr = sortByValueDescending(singleSemiJobMap);

                for (String semiJobId : singleSemiJobArr) {
                    if (!currentStudentApplicantJobIdList.contains(semiJobId)) {
                        Job job = jobService.getJob(new ObjectId(semiJobId));
                        finalSemiJobSet.add(job);
                        itemBasedSum++;
                    }
                }
            }
            System.out.println("基于物品的协同过滤推荐 = " + itemBasedSum);

            ArrayList<Job> finalSemiJobList = new ArrayList<>(finalSemiJobSet);
            for (Job job : finalSemiJobList) {
                System.out.println(job.getJobName() + " " + job.getCompanyName() + " " + job.getId().toString());
            }

            updateStudentRecommendList(student.getAccount(), finalSemiJobList);
        }

    }

}
