package com.hnist.yang.myapplication;

import cn.bmob.v3.BmobObject;

public class Mycsdn extends BmobObject{
    private String head_name;//博客名字
    private String topic_content;//博客内容
    private String keyWords;//关键词
    private String visit_number;//博客访问量

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;//种类
    public String getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(String belongTo) {
        this.belongTo = belongTo;
    }

    private String belongTo;
    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }


    public String getHead_name() {
        return head_name;
    }

    public void setHead_name(String head_name) {
        this.head_name = head_name;
    }

    public String getTopic_content() {
        return topic_content;
    }

    public void setTopic_content(String topic_content) {
        this.topic_content = topic_content;
    }

    public String getVisit_number() {
        return visit_number;
    }

    public void setVisit_number(String visit_number) {
        this.visit_number = visit_number;
    }




}
