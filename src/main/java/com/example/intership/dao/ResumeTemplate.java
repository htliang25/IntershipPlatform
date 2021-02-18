package com.example.intership.dao;

import com.example.intership.entities.Accessory;
import com.example.intership.entities.Form;
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

        switch (colName) {
            case "informationForm":
                Form form = mongoTemplate.findOne(query, InformationForm.class, colName);
                data = form.getForm();
                break;
            case "jobForm":
                form = mongoTemplate.findOne(query, JobForm.class, colName);
                data = form.getForm();
                break;
            case "abilityContent":
                form = mongoTemplate.findOne(query, AbilityContent.class, colName);
                data = form.getForm();
                break;
            case "evaluationContent":
                form = mongoTemplate.findOne(query, EvaluationContent.class, colName);
                data = form.getForm();
                break;
            case "paperContent":
                form = mongoTemplate.findOne(query, PaperContent.class, colName);
                data = form.getForm();
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
            case "abilityContent":
                update = AbilityContent.modify(data);
                break;
            case "evaluationContent":
                update = EvaluationContent.modify(data);
                break;
            case "paperContent":
                update = PaperContent.modify(data);
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


    public void deleteMultipleForm(String account, String colName) {
        Criteria criteria = Criteria.where("account").is(account);
        Query query = new Query(criteria);

        switch (colName) {
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
            default:
                break;
        }
    }

    public ArrayList getAccessory(String account) {
        Criteria criteria = Criteria.where("account").is(account);
        Query query = new Query(criteria);

        ArrayList data = new ArrayList();

        List<Accessory> accessories = mongoTemplate.find(query, Accessory.class, "accessory");
        for(Accessory accessory : accessories) {
            data.add(accessory.getFile());
        }

        return data;
    }

    public void saveAccessory(Accessory accessory) {
        mongoTemplate.save(accessory);
    }

    public void deleteAccessory(String account) {
        Criteria criteria = Criteria.where("account").is(account);
        Query query = new Query(criteria);

        mongoTemplate.remove(query, Accessory.class);
    }

}
