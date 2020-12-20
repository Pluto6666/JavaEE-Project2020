package com.tongji.javaEE.Domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "car_vin_data")
public class carVinData {
    @Id
    private String carVin;
    @Column(name = "brand")
    public String brand;
    @Column(name = "car_name")
    public String carName;
    @Column(name = "color")
    public String color;
    @Column(name = "displacement")
    private float displacement ;
    @Column(name = "date_produce")
    public String dateProduce ;
}
