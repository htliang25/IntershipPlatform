package com.example.intership;

import com.example.intership.dao.JobTemplate;
import com.example.intership.dao.UserTemplate;
import com.example.intership.entities.user.Applicant;
import com.example.intership.entities.form.Form;
import com.example.intership.entities.job.Job;
import com.example.intership.entities.form.singleform.JobForm;
import com.example.intership.entities.user.Enterprise;
import com.example.intership.entities.user.Student;
import com.example.intership.service.JobService;
import com.example.intership.service.ResumeService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
class IntershipApplicationTests {

    @Autowired
    UserTemplate userTemplate;

    @Autowired
    JobTemplate jobTemplate;

    @Autowired
    JobService jobService;

    @Autowired
    ResumeService resumeService;

    @Test
    void contextLoads(){

        String []companyArr = new String[]{"字节跳动", "腾讯", "阿里巴巴", "美团", "快手", "拼多多", "滴滴", "斗鱼", "百度", "虎牙", "华为", "深信服", "哔哩哔哩", "微软", "Google"};
        String []cityArr = new String[]{"北京", "上海", "深圳", "杭州", "南京", "成都", "厦门", "武汉", "广州", "天津", "其他"};
        String []typeArr = new String[]{"测试", "数据分析", "算法", "前端", "后端", "客户端", "游戏开发", "产品经理", "运营", "行政", "人事", "美术设计", "商务合作",  "硬件设施", "法务"};
        String []companyTypeArr = new String[]{"互联网", "游戏", "咨询", "直播", "应用服务", "硬件设施", "算法", "视频", "搜索"};
        String []companyAddressArr = new String[]{"北京", "上海", "深圳", "杭州", "南京", "成都", "厦门", "武汉", "广州", "天津", "其他"};
        String []majorArr = new String[]{"管理科学与工程", "工商管理", "公关管理", "电子信息科学", "机械", "经济学", "语言文学", "艺术", "计算机科学与技术", "新闻传播学", "心理学", "数学", "物理学", "法学"};
        String []entryTimeArr = new String[]{"本周", "本月", "三个月内", "半年内", "一年内"};


        for (int i = 1;i <= 10;i++) {
            Student user = new Student();
            user.setRole(1);
            user.setAccount("student"+ i);
            user.setPwd("password" + i);
            user.setUniversity("university" + i);
            user.setMajor(majorArr[(int) (majorArr.length * Math.random())]);
            userTemplate.saveUser(user);
        }

        Student user1 = new Student();
        user1.setRole(1);
        user1.setAccount("LMS");
        user1.setPwd("123456");
        user1.setUniversity("华南师范大学");
        user1.setMajor("计算机科学与技术");
        userTemplate.saveUser(user1);


        for (int i = 0;i < companyArr.length;i++) {
            Enterprise user = new Enterprise();
            user.setRole(2);
            user.setAccount(companyArr[i]);
            user.setPwd("123456");
            user.setCompanyName(companyArr[i]);
            user.setCompanyIntro("companyIntro: " + companyArr[i]);
            user.setCompanyType(companyTypeArr[(int)(Math.random() * companyTypeArr.length)]);
            user.setCompanyAddress(companyAddressArr[(int)(Math.random() * companyAddressArr.length)]);
            userTemplate.saveUser(user);
        }


        for (int j = 1;j < 100;j++) {
            int companyNumber = (int)(Math.random() * companyArr.length);
            Job job = new Job(companyArr[companyNumber], companyArr[companyNumber]);
            int typeNumber = (int)(Math.random() * typeArr.length);
            job.setJobCity(cityArr[(int)(Math.random() * cityArr.length)]);
            job.setJobType(typeArr[typeNumber]);
            job.setJobDuty("职责: 1.认真 2.负责");
            job.setJobDescription("描述: 1.超级认真 2.超级负责");
            job.setJobName(typeArr[typeNumber]+ "实习生");
            job.setJobRequire("要求: 1.究极认真 2.究极负责");
            jobTemplate.publishJob(job);
        }


        // 学生投简历到岗位  学生一投多
        List<Student> studentList = userTemplate.getStudentList();
        List<Job> jobList = jobService.getJobList("全国", "全部");
        for (int i = 0; i < studentList.size(); i++) {

            for (int j = 0;j < 10;j++) {
                Student student = studentList.get(i);
                Job job = jobList.get((int) (jobList.size() * Math.random()));
                String account = student.getAccount();
                String entryTime = entryTimeArr[(int) (entryTimeArr.length * Math.random())];
                ObjectId jobId = job.getId();

                Applicant applicant = new Applicant();
                applicant.setApplicantAccount(account);
                applicant.setEntryTime(entryTime);
                applicant.setJobId(jobId.toString());

                jobService.addApplicant(jobId, applicant);
            }
        }


        for (int i = 0; i < studentList.size() - 1; i++) {
            Student student = studentList.get(i);
            String account = student.getAccount();

            HashMap<String, Object> jobFormData = new HashMap<>();
            jobFormData.put("address", cityArr[(int) (cityArr.length * Math.random())]);
            jobFormData.put("job", typeArr[(int) (typeArr.length * Math.random())]);
            Form form = new JobForm(account);
            form.setAttributes(jobFormData);
            resumeService.saveForm(form, "jobForm");
        }

    }

}
