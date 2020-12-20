package com.tongji.javaEE.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.tongji.javaEE.Domain.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BuyingRepository extends JpaRepository<Buying_leads, String>{
    List<Buying_leads> findByUserId(String userId);
    List<Buying_leads> findByCarName(String carName);

    @Modifying
    @Transactional
    public int deleteByBuyId(String buyId);
}
