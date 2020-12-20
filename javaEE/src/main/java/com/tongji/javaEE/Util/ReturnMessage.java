package com.tongji.javaEE.Util;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class ReturnMessage implements Serializable{
    private int code;
    private String message;

    public ReturnMessage(int code,String message){
        this.code=code;
        this.message=message;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
