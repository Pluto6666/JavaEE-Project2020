package com.tongji.javaEE.Dao;
import com.tongji.javaEE.Domain.auditData;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditDataRepository extends JpaRepository<auditData,String>{

   @Override
   List<auditData> findAll();
   List<auditData> findByCarVin(String carVin);
   List<auditData> findAllByUserId(String userId);

   // @Override
    //public Audit_data findByCarVin(String carVin);
}
