package com.tongji.javaEE.Util;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataReturnMessage<T> implements Serializable {
    private int code;
    private String message;
    private T data;
    public DataReturnMessage(int code,String message){
        this.code=code;
        this.message=message;
    };

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(T data) {
        this.data = data;
    }
}

