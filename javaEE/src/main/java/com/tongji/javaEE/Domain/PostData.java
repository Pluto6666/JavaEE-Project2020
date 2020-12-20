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
@Table(name = "post_data")
public class PostData implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "post_id")
    private String postId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "title")
    private String title;
    @Column(name = "post_date")
    private String postDate;
    @Column(name = "content")
    private String content;

    public PostData(){ super(); }

    public PostData(String postId, String userId, String title, String postDate, String content){
        this.postId=postId;
        this.userId=userId;
        this.title=title;
        this.postDate=postDate;
        this.content=content;
    }

    public String getPostId() {
        return postId;
    }
    public void setPostId(String postId) {
        this.postId = postId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getPostDate() {
        return postDate;
    }
    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

}
