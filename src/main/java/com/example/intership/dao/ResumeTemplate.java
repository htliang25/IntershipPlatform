package com.example.intership.dao;

import com.example.intership.entities.Accessory;
import com.example.intership.entities.Content;
import com.example.intership.entities.Form;
import com.example.intership.entities.content.AbilityContent;
import com.example.intership.entities.content.EvaluationContent;
import com.example.intership.entities.content.PaperContent;
import com.example.intership.entities.multipleform.AwardExperience;
import com.example.intership.entities.multipleform.EducationExperience;
import com.example.intership.entities.multipleform.ProjectExperience;
import com.example.intership.entities.multipleform.SchoolExperience;
import com.example.intership.entities.singleform.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ResumeTemplate {
    @Autowired
    MongoTemplate mongoTemplate;

    public Map<String, Object> getSingleForm(String account, String colName) {
        Criteria criteria = Criteria.where("account").is(account);
        Query query = new Query(criteria);

        Map<String, Object> data = new HashMap<>();

        Form form;
        switch (colName) {
            case "informationForm":
                form = mongoTemplate.findOne(query, InformationForm.class, colName);
                if (form != null) {
                    data = form.getForm();
                }
                break;
            case "jobForm":
                form = mongoTemplate.findOne(query, JobForm.class, colName);
                if (form != null) {
                    data = form.getForm();
                }
                break;
            default:
                break;
        }

        return data;
    }

    public void saveForm(Form form, String colName) {
        mongoTemplate.save(form, colName);
    }

    public void modifySingleForm(String account, String colName, Map<String, Object> data) {
        Criteria criteria = Criteria.where("account").is(account);
        Query query = new Query(criteria);
        Update update = new Update();

        switch (colName) {
            case "informationForm":
                update = InformationForm.modify(data);
                break;
            case "jobForm":
                update = JobForm.modify(data);
                break;
            default:
                break;
        }

        mongoTemplate.updateMulti(query, update, colName);
    }

    public String getContent(String account, String colName) {
        Criteria criteria = Criteria.where("account").is(account);
        Query query = new Query(criteria);

        String data = "";

        switch (colName) {
            case "abilityContent":
                Content content = mongoTemplate.findOne(query, AbilityContent.class, colName);
                if (content != null) {
                    data = content.getContent();
                }
                break;
            case "evaluationContent":
                content = mongoTemplate.findOne(query, EvaluationContent.class, colName);
                if (content != null) {
                    data = content.getContent();
                }
                break;
            case "paperContent":
                content = mongoTemplate.findOne(query, PaperContent.class, colName);
                if (content != null) {
                    data = content.getContent();
                }
                break;
            default:
                break;
        }

        return data;
    }

    public void saveContent(Content content, String colName) {
        mongoTemplate.save(content, colName);
    }

    public void modifyContent(String account, String colName, String data) {
        Criteria criteria = Criteria.where("account").is(account);
        Query query = new Query(criteria);
        Update update = new Update();

        switch (colName) {
            case "abilityContent":
                update = AbilityContent.modifyContent(data);
                break;
            case "evaluationContent":
                update = EvaluationContent.modifyContent(data);
                break;
            case "paperContent":
                update = PaperContent.modifyContent(data);
                break;
            default:
                break;
        }

        mongoTemplate.updateMulti(query, update, colName);
    }

    public ArrayList getMultipleForm(String account, String colName) {
        Criteria criteria = Criteria.where("account").is(account);
        Query query = new Query(criteria);

        ArrayList data = new ArrayList();

        switch (colName) {
            case "educationExperience":
                List<EducationExperience> multipleForm = mongoTemplate.find(query, EducationExperience.class, colName);
                for (EducationExperience educationExperience : multipleForm) {
                    data.add(educationExperience.getForm());
                }
                break;
            case "schoolExperience":
                List<SchoolExperience> multipleForm1 = mongoTemplate.find(query, SchoolExperience.class, colName);
                for (SchoolExperience schoolExperience : multipleForm1) {
                    data.add(schoolExperience.getForm());
                }
                break;
            case "projectExperience":
                List<ProjectExperience> multipleForm2 = mongoTemplate.find(query, ProjectExperience.class, colName);
                for (ProjectExperience projectExperience : multipleForm2) {
                    data.add(projectExperience.getForm());
                }
                break;
            case "awardExperience":
                List<AwardExperience> multipleForm3 = mongoTemplate.find(query, AwardExperience.class, colName);
                for (AwardExperience awardExperience : multipleForm3) {
                    data.add(awardExperience.getForm());
                }
                break;
            default:
                break;
        }

        return data;
    }

    public void deleteInfo(String account, String colName) {
        Criteria criteria = Criteria.where("account").is(account);
        Query query = new Query(criteria);

        switch (colName) {
            case "informationForm":
                mongoTemplate.remove(query, InformationForm.class);
                break;
            case "jobForm":
                mongoTemplate.remove(query, JobForm.class);
                break;
            case "educationExperience":
                mongoTemplate.remove(query, EducationExperience.class);
                break;
            case "schoolExperience":
                mongoTemplate.remove(query, SchoolExperience.class);
                break;
            case "projectExperience":
                mongoTemplate.remove(query, ProjectExperience.class);
                break;
            case "awardExperience":
                mongoTemplate.remove(query, AwardExperience.class);
                break;
            case "abilityContent":
                mongoTemplate.remove(query, AbilityContent.class);
                break;
            case "evaluationContent":
                mongoTemplate.remove(query, EvaluationContent.class);
                break;
            case "paperContent":
                mongoTemplate.remove(query, PaperContent.class);
                break;
            case "avatar":
            case "accessory":
                mongoTemplate.remove(query, Accessory.class, colName);
                break;
            default:
                break;
        }
    }

    public boolean isExist(String account, String colName) {
        Criteria criteria = Criteria.where("account").is(account);
        Query query = new Query(criteria);

        boolean result = false;

        switch (colName) {
            case "informationForm":
                Form form = mongoTemplate.findOne(query, InformationForm.class, colName);
                result = (form != null) ? true : false;
                break;
            case "educationExperience":
                form = mongoTemplate.findOne(query, EducationExperience.class, colName);
                result = (form != null) ? true : false;
                break;
            case "schoolExperience":
                form = mongoTemplate.findOne(query, SchoolExperience.class, colName);
                result = (form != null) ? true : false;
                break;
            case "projectExperience":
                form = mongoTemplate.findOne(query, ProjectExperience.class, colName);
                result = (form != null) ? true : false;
                break;
            case "awardExperience":
                form = mongoTemplate.findOne(query, AwardExperience.class, colName);
                result = (form != null) ? true : false;
                break;
            case "jobForm":
                form = mongoTemplate.findOne(query, JobForm.class, colName);
                result = (form != null) ? true : false;
                break;
            case "abilityContent":
                Content content = mongoTemplate.findOne(query, AbilityContent.class, colName);
                result = (content != null) ? true : false;
                break;
            case "evaluationContent":
                content = mongoTemplate.findOne(query, EvaluationContent.class, colName);
                result = (content != null) ? true : false;
                break;
            case "paperContent":
                content = mongoTemplate.findOne(query, PaperContent.class, colName);
                result = (content != null) ? true : false;
                break;
            default:
                break;
        }

        return result;
    }
}
