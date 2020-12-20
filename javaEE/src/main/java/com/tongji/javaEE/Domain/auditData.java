package com.tongji.javaEE.Domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @author xjt
 */
@Entity
@Table(name = "audit_data")
@Data
public class auditData implements Serializable{
    @Id
    private String carVin;
    @Column(name = "status")// 0 待审核 1 不通过 2通过 3 已被下单
    private int status;
    @Column(name = "car_name")
    private String carName;
    @Column(name = "brand")
    private String brand;
    @Column(name = "date_produce")
    private String date_produce;
    @Column(name = "date_buyin")
    private String dateBuyin;
    @Column(name = "color")
    private String color;
    @Column(name = "displacement")
    private float displacement;
    @Column(name = "car_condition")
    private String carCondition;
    @Column(name = "check_data")
    private String checkData;
    @Column(name = "price")
    private int price;
    @Column(name = "accident")
    private String accident;
    @Column(name = "kilometer")
    private int kilometer;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "phone")
    private String phone;
    @Column(name = "url")
    private String url;

}
