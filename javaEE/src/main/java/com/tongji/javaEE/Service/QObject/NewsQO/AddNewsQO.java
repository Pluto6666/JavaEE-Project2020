package com.tongji.javaEE.Service.QObject.NewsQO;

import java.io.Serializable;

public class AddNewsQO implements Serializable {
    public String news_id;  //新闻id
    public String title;   //新闻标题
    public String content; //新闻内容
    public String part;    //新闻分区
    public String author_id;//作者id
    public String author_name;//作者姓名
    public String post_date;//发布日期

}
