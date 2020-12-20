package com.tongji.javaEE.Util;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class pageQueryData<T> implements Serializable {
    private int total;
    private int pageNum;
    private T data;
    public pageQueryData (){};

    public T getData() {
        return data;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getTotal() {
        return total;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
