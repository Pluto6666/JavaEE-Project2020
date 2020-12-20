package com.tongji.javaEE.Domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class WebFile {
    private String status;
    private String filepath;

    public WebFile(){
        super();
    }
}
