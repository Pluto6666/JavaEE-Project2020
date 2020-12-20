package com.tongji.javaEE.Dao;

import com.tongji.javaEE.Domain.*;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<Order_data, String>{
    List<Order_data> findByBuyerId(String buyerId);
    List<Order_data> findByCarVin(String carVin);

    @Modifying
    @Transactional
    public void deleteByCarVin(String carVin);
}
