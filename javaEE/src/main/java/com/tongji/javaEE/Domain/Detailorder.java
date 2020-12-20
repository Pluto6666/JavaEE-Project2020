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

public class Detailorder {
    private String carVin;
    private String buyerId;
    private String sellerId;
    private int price;

    public Detailorder(){super();}
}
