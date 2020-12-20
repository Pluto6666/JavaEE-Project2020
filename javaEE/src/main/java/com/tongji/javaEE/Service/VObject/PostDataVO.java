package com.tongji.javaEE.Service.VObject;

import java.io.Serializable;

public class PostDataVO implements Serializable {
    public String post_id;
    public String user_id;
    public String user_name;
    public String post_date;
    public String title;
    public String content;

    public PostDataVO(String post_id,String user_id,String user_name,String post_date,String title,String content){
        this.post_id=post_id;
        this.user_id=user_id;
        this.user_name=user_name;
        this.post_date=post_date;
        this.title=title;
        this.content=content;
    }


}
