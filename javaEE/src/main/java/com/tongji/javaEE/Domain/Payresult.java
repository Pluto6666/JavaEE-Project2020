package com.tongji.javaEE.Domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

public class Payresult {
    private int status;
    private int money;

    public Payresult(){super();}
}
