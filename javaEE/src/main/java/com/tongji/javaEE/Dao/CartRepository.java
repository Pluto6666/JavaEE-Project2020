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
public interface CartRepository extends JpaRepository<Cart, Long>{
    @Query("SELECT new com.tongji.javaEE.Domain.Mycart(t.carVin, t.carCondition, t.price,t.userId) FROM carData as t inner join Cart as a on a.carVin=t.carVin WHERE a.userId = :userId")
    public List<Mycart> findCarDataByUserId(@Param("userId") String UserId);

    List<Cart> findCartByCarVinEqualsAndUserIdEquals(String carVin,String userId);

    @Modifying
    @Transactional
    void deleteByCarVinAndUserId(String carVin,String userId);

    @Modifying
    @Transactional
    void deleteByCarVin(String carVin);
}
