package com.tongji.javaEE.Service.QObject;

import java.io.Serializable;

public class AddForumReplyQO implements Serializable {
    public String reply_id;//回复id
    public String user_id;//用户id
    public String post_id;//回复的主题帖id
    public String reply_date;//发布时间
    public String content;//回复内容
}
