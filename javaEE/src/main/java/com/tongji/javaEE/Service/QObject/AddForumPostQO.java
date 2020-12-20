package com.tongji.javaEE.Service.QObject;

import java.io.Serializable;

public class AddForumPostQO implements Serializable {
    public String post_id;//主题帖id
    public String user_id;//用户id
    public String post_date;//发布时间
    public String title;//主题帖标题
    public String content;//主题帖内容
}
