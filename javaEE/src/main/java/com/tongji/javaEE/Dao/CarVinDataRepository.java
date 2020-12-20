package com.tongji.javaEE.Dao;

import com.tongji.javaEE.Domain.carVinData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarVinDataRepository extends JpaRepository<carVinData,String>{

}
