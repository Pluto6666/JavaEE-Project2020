package com.tongji.javaEE.Domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "vip_data")
public class Vip implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "vip_id")
    private String vipId;

    @Column(name = "vip_level")
    private int vipLevel;
    @Column(name = "begin_time")
    private String beginTime;
    @Column(name = "end_time")
    private String endTime;

    public Vip(){super();}

    public String getVipId() {
        return vipId;
    }

    public int getVipLevel() {
        return vipLevel;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setVipId(String vipId) {
        this.vipId = vipId;
    }

    public void setVipLevel(int vipLevel) {
        this.vipLevel = vipLevel;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
