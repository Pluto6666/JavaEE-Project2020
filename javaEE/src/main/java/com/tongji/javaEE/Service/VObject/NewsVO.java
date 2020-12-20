package com.tongji.javaEE.Service.VObject;

import java.io.Serializable;

/**
 * 用于展示的新闻信息
 */
public class NewsVO implements Serializable {
    public String news_id ;
    public String title ;
    public String content ;
    public String part ;
    public String author_id ;
    public String author_name ;
    public String post_date ;

    public NewsVO(String news_id,String title,String content,String part,String author_id,String author_name,String post_date){
        this.news_id=news_id;
        this.title=title;
        this.post_date=post_date;
        this.author_id=author_id;
        this.author_name=author_name;
        this.content=content;
        this.part=part;
    }

}
