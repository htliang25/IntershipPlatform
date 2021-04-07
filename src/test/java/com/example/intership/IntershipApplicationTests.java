package com.example.intership;

import com.example.intership.dao.JobTemplate;
import com.example.intership.dao.UserTemplate;
import com.example.intership.entities.job.Applicant;
import com.example.intership.entities.job.Job;
import com.example.intership.entities.resume.Form;
import com.example.intership.entities.resume.singleform.JobForm;
import com.example.intership.entities.user.Enterprise;
import com.example.intership.entities.user.Student;
import com.example.intership.service.EnterpriseService;
import com.example.intership.service.JobService;
import com.example.intership.service.ResumeService;
import com.example.intership.service.StudentService;
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
    EnterpriseService enterpriseService;

    @Autowired
    JobService jobService;

    @Autowired
    ResumeService resumeService;

    @Autowired
    StudentService studentService;

    @Test
    void contextLoads(){

        String []companyArr = new String[]{"字节跳动", "腾讯", "阿里巴巴", "美团", "快手", "拼多多", "滴滴", "斗鱼", "百度", "虎牙", "华为", "深信服", "哔哩哔哩", "微软", "Google"};
        String []cityArr = new String[]{"北京", "上海", "深圳", "杭州", "南京", "成都", "厦门", "武汉", "广州", "天津", "其他"};
        String []typeArr = new String[]{"测试", "数据分析", "算法", "前端", "后端", "客户端", "游戏开发", "产品经理", "运营", "行政", "人事", "美术设计", "商务合作",  "硬件设施", "法务"};
        String []companyTypeArr = new String[]{"互联网", "游戏", "咨询", "直播", "应用服务", "硬件设施", "算法", "视频", "搜索"};
        String []companyAddressArr = new String[]{"北京", "上海", "深圳", "杭州", "南京", "成都", "厦门", "武汉", "广州", "天津", "其他"};
        String []companyIntroArr = new String[]{
                "字节跳动成立于2012年3月，目前公司的产品和服务已覆盖全球150个国家和地区，75个语种，曾在40多个国家和地区排在应用商店总榜前列。 字节跳动在海内外推出了多款有影响力的产品，包括综合资讯类的今日头条，TopBuzz，新闻共和国，视频类的抖音，TikTok，西瓜视频，BuzzVideo，火山小视频，等新业务。",
                "深圳市腾讯计算机系统有限公司（腾讯）是一家民营IT企业，成立于1998年11月11日，总部位于中国广东深圳，是中国最大的互联网综合服务提供商之一，也是中国服务用户最多，最广的互联网企业之一。",
                "阿里巴巴集团经营多元化的互联网业务，致力为全球所有人创造便捷的交易渠道。自成立以来，阿里巴巴集团建立了领先的消费者电子商务、网上支付、B2B网上交易市场及云计算业务，近几年更积极开拓无线应用、手机操作系统和互联网电视等领域。",
                "美团的使命是“帮大家吃得更好，生活更好”。作为一家生活服务电子商务平台，公司聚焦“Food +Platform”战略，以“吃”为核心，通过科技创新，和广大商户与各类合作伙伴一起，努力为消费者提供品质生活，推动生活服务业需求侧和供给侧数字化升级。 2018年9月20日，美团正式在港交所挂牌上市。美团将始终坚持以客户为中心，不断加大在科技研发方面的投入，更好承担社会责任，更多创造社会价值，与广大合作伙伴一起发展共赢。",
                "快手是面向普通人的记录和分享生活的短视频社交平台。以“拥抱每一种生活”，用有温度的科技提升每个人独特的幸福感为核心使命。",
                "拼多多作为新电商开创者，致力于将娱乐社交的元素融入电商运营中，通过“社交+电商”的模式，让更多的用户带着乐趣分享实惠，享受全新的共享式购物体验。",
                "滴滴出行是涵盖出租车、专车、快车、顺风车、代驾及大巴等多项业务在内的一站式出行平台",
                "斗鱼TV是一家弹幕式直播分享网站，为用户提供视频直播和赛事直播服务。斗鱼TV的前身为ACFUN生放送直播，于2014年1月1日起正式更名为斗鱼TV。斗鱼TV以游戏直播为主，涵盖了娱乐、综艺、体育、户外等多种直播内容。",
                "百度（纳斯达克：BIDU），全球最大的中文搜索引擎、最大的中文网站。百度愿景是：成为最懂用户，并能帮助人们成长的全球顶级高科技公司。 百度拥有数万名研发工程师，这是中国乃至全球最为优秀的技术团队。这支队伍掌握着世界上最为先进的搜索引擎技术，使百度成为中国掌握世界尖端科学核心技术的中国高科技企业。",
                "虎牙直播（纽交所简称：HUYA）是中国领先的弹幕式直播互动平台，以游戏直播为主，涵盖娱乐、综艺、教育、户外、体育等多种直播内容。2018年3月，虎牙直播获得腾讯战略投资4.62亿美元的B轮融资。2018年5月，虎牙直播正式在纽约证券交易所挂牌交易；海外直播Nimo TV登陆东南亚，成为东南亚第一游戏直播平台。6月29日，虎牙直播与腾讯游戏签署战略协议。",
                "华为技术有限公司是一家生产销售通信设备的民营通信科技公司，总部位于中国广东省深圳市龙岗区坂田华为基地。华为的产品主要涉及通信网络中的交换网络、传输网络、无线及有线固定接入网络和数据通信网络及无线终端产品，为世界各地通信运营商及专业网络拥有者提供硬件设备、软件、服务和解决方案。",
                "深信服公司是专注于云计算／虚拟化、网络安全领域的IT解决方案服务商，致力于提供创新的IT基础设施虚拟化与云计算建设解决方案，推出的众多产品中，其中安全系列产品中国市场占有率第一，在2011年初，公司全面进入云计算、虚拟化行业，目前多个云计算产品入围gartner魔力象限。",
                "哔哩哔哩（bilibili）是中国领先的年轻人文化社区，该网站于2009年6月26日创建，被粉丝们亲切的称为“B站”。 根据数据公司QuestMobile发布的《移动互联网2017年Q2夏季报告》，B站位列24岁及以下年轻用户偏爱的十大APP榜首。同时，在百度发布的2016热搜榜中，B站在00后十大新鲜关注APP中排名第一。",
                "微软 (Microsoft)，是一家总部位于美国的跨国电脑科技公司，是世界PC（Personal Computer，个人计算机）机软件开发的先导，由比尔·盖茨与保罗·艾伦创始于1975年，公司总部设立在华盛顿州的雷德蒙德市（Redmond，邻 近西雅图）。以研发、制造、授权和提供广泛的电脑软件服务业务为主。",
                "Google公司（中文译名：谷歌），是一家美国的跨国科技企业，致力于互联网搜索、云计算、广告技术等领域，开发并提供大量基于互联网的产品与服务，其主要利润来自于AdWords等广告服务。"
        };

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
            user.setCompanyIntro(companyIntroArr[i]);
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
            jobService.publishJob(job);
            enterpriseService.publishJob(companyArr[companyNumber], job.getId());
        }


        // 学生投简历到岗位  学生一投多
        List<Student> studentList = userTemplate.getStudentList();
        List<Job> jobList = jobService.getJobList("全国", "全部");
        for (int i = 0; i < studentList.size(); i++) {

            for (int j = 0;j < 5;j++) {
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
                jobService.addApplicant(jobId, applicant);
                studentService.addJob(account, jobId);
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
