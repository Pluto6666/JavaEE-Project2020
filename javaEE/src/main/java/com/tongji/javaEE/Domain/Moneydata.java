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
@Table(name = "money_data")
public class Moneydata {
    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(name = "money")
    private int Money;

    public Moneydata(){super();}
}