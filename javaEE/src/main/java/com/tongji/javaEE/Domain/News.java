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
@Table(name = "news")
public class News implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "title")
    private String title;//新闻标题
    @Column(name = "author_id")
    private String authorId;
    @Column(name = "author_name")
    private String authorName;
    @Column(name = "post_date")
    private String postDate;
    @Column(name = "part")
    private String part;//新闻分区
    @Column(name = "content")
    private String content;
    @Column(name = "reader_num")
    private int readerNum;
    @Column(name = "craze")
    private int craze;//热度

    public News(){
        super();
    }

    public News(String id,String title,String content,String part,String authorId,String authorName,String postDate){
        this.id=id;
        this.title=title;
        this.content=content;
        this.part=part;
        this.authorId=authorId;
        this.authorName=authorName;
        this.postDate=postDate;
        this.readerNum=0;
        this.craze=0;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthorId() {
        return authorId;
    }
    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public String getPostDate() {
        return postDate;
    }
    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }
    public String getPart() {
        return part;
    }
    public void setPart(String partiton) {
        this.part = partiton;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getReaderNum() {
        return readerNum;
    }
    public void setReaderNum(int readerNum) {
        this.readerNum = readerNum;
    }
    public int getCraze() {
        return craze;
    }
    public void setCraze(int craze) {
        this.craze = craze;
    }
}
