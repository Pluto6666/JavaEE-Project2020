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
@Table(name = "order_data")
public class Order_data {
    @Id
    @Column(name = "order_id")
    private String orderId;
    @Column(name = "buyer_id")
    private String buyerId;
    @Column(name = "seller_id")
    private String sellerId;
    @Column(name = "price")
    private int price;
    @Column(name = "car_vin")
    private String carVin;
    @Column(name = "date_order")
    private String dateOrder;
    @Column(name = "status")
    private int status;

    public Order_data(){super();}
}
