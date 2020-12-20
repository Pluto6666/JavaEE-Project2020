package com.tongji.javaEE.Service.VObject;

import java.io.Serializable;

public class ReplyDataVO implements Serializable {
    public String reply_id;
    public String user_id;
    public String user_name;
    public String reply_date;
    public String content;

    public ReplyDataVO(String reply_id,String user_id,String user_name,String reply_date,String content){
        this.reply_id=reply_id;
        this.user_id=user_id;
        this.user_name=user_name;
        this.reply_date=reply_date;
        this.content=content;
    }

}
