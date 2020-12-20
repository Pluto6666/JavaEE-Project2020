package com.tongji.javaEE.Service.VObject;

import java.io.Serializable;
import java.util.List;

public class NewsPageVO implements Serializable {
    public int total;
    public int page_num;
    public List<NewsVO> newsSet;

    public NewsPageVO(int total,int page_num,List<NewsVO> newsSet){
        this.total=total;
        this.page_num=page_num;
        this.newsSet=newsSet;
    }
}
