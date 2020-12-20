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
@Entity
@Table(name = "buying_leads")
public class Buying_leads {

    @Id
    @Column(name = "buy_id")
    private String buyId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "brand")
    private String brand;
    @Column(name = "color")
    private String color;
    @Column(name = "car_name")
    private String carName;
    @Column(name = "displacement")
    private int displacement;
    @Column(name = "car_condition")
    private String car_condition;
    @Column(name = "price")
    private int price;
    @Column(name = "date_produce")
    private String date;
    @Column(name = "accident")
    private String accident;
    @Column(name = "phone")
    private String phone;
    @Column(name = "kilometer")
    private int kilometer;

    public Buying_leads(){
        super();
    }
}
