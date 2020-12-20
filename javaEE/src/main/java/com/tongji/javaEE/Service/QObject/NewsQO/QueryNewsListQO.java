package com.tongji.javaEE.Service.QObject.NewsQO;

import java.io.Serializable;

public class QueryNewsListQO implements Serializable {
    public String keyword;//关键词，可为空
    public String author_id;//作者id，可为空
    public int page_num;//当前页码
    public int page_size;//每页显示数目
}
