package com.tongji.javaEE.Domain;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Mycart {
    private String carVin;
    private String car_condition;
    private int price;
    private String userId;

    public Mycart(){super();}

    public Mycart(String carVin,String car_condition,int price,String userId){
        this.carVin=carVin;
        this.car_condition=car_condition;
        this.price=price;
        this.userId=userId;
    }
}
