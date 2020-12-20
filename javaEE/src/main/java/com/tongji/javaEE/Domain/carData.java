package com.tongji.javaEE.Domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "car_data")
public class carData {
    @Id
    private String carVin;
    @Column(name = "car_condition")
    private String carCondition;
    @Column(name = "date_buyin")
    private String dateBuyin;
    @Column(name = "accident")
    private String accident;
    @Column(name = "price")
    private int price;
    @Column(name = "phone_number")//改了？
    private String phoneNumber;
    @Column(name = "user_id")
    public String userId ;
    @Column(name = "kilometer")
    private int kilometer;
    @Column(name = "iforder")
    private int iforder;
    //是否被下单 iforder =0未被下单 iforder=1 被下单了 不能再被下单

}
