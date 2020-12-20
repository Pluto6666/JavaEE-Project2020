package com.tongji.javaEE.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "reply_data")
public class ReplyData implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "reply_id")
    private String replyId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "post_id")
    private String postId;
    @Column(name = "reply_date")
    private String replyDate;
    @Column(name = "content")
    private String content;

    public ReplyData(){ super(); }

    public ReplyData(String replyId,String userId,String postId,String replyDate,String content){
        this.replyId=replyId;
        this.userId=userId;
        this.postId=postId;
        this.replyDate=replyDate;
        this.content=content;
    }

    public String getReplyId() {
        return replyId;
    }
    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getPostId() {
        return postId;
    }
    public void setPostId(String postId) {
        this.postId = postId;
    }
    public String getReplyDate() {
        return replyDate;
    }
    public void setReplyDate(String replyDate) {
        this.replyDate = replyDate;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

}
