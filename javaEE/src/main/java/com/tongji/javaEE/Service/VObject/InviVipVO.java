package com.tongji.javaEE.Service.VObject;


import com.tongji.javaEE.Domain.Vip;

public class InviVipVO {
    public String user_name;
    public  String vip_id;
    public int vip_level;
    public String start_time;
    public String end_time;

    public InviVipVO(String user_name, Vip vip){
        this.user_name=user_name;
        this.vip_id=vip.getVipId();
        this.vip_level=vip.getVipLevel();
        this.start_time=vip.getBeginTime();
        this.end_time=vip.getEndTime();
    };
}
