package com.tongji.javaEE.Dao;

import com.tongji.javaEE.Domain.carData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CarDataRepository extends JpaRepository<carData,String>{
    List<carData> findByCarVin(String carVin);
}
