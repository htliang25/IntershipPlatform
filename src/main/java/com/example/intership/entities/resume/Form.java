package com.example.intership.entities.resume;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;

import java.util.Map;
import java.util.Objects;

public abstract class Form {
    /*
        表格抽象类
        继承类有个人信息类、教育经历类、校内经历类、求职意向类、项目经历类和获奖情况类
        类属性有id、账户account
        类函数有类属性的set、get函数、设置表格属性函数、获取表格函数和判断对象是否为空的函数
        重载了比较函数
     */
    private ObjectId _id;
    private String account;

    public Form(String account) {
        _id = new ObjectId();
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Form form = (Form) o;
        return Objects.equals(_id, form._id) && Objects.equals(account, form.account);
    }

    public abstract void setAttributes(Map<String, Object> data);

    public abstract Map<String, Object> getForm();

    public ObjectId getId() {
        return _id;
    }

    public String getAccount() {
        return account;
    }

    public static boolean isObjectNotEmpty(Object obj) {
        String str = ObjectUtils.toString(obj, "");
        return StringUtils.isNotBlank(str);
    }
}
