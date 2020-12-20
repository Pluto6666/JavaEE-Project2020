package com.tongji.javaEE.Dao;


import com.tongji.javaEE.Domain.Vip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VipRepository extends JpaRepository<Vip,String> {
    Vip findByVipId(String vipId);
    Vip findByVipLevel(int level);
}
