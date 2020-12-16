package com.example.demo.Model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataReturnMessage<T> implements Serializable {
    private int code;
    private String message;
    private T data;
}
