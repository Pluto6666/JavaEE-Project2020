package com.tongji.javaEE.Domain;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Format<T> {
    private int code;
    private String message;
    private List<T> data;

    public Format(){
        super();
    }
}
