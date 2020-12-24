package com.tongji.javaEE.Dao;

import com.tongji.javaEE.Domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission,String> {
    //查询小于等于传入会员等级的权限
//    @Query("select new com.tongji.javaEE.Service.VObject.PermissionVO"+
//            "(l.permissions,l.vip_level)"+
//            " from Permission l"+
//            " where l.vip_level <= :vip_level"+
//            "orderby l.vip_level descending"
//    )
//    List<PermissionVO> findLessThanLevel(@Param("vip_level")String vip_level);
    @Query(value = "select o from Permission o where o.vipLevel<=:vip_level order by o.vipLevel desc")
    List<Permission> findByVipLevelLessThanEqual(@Param("vip_level") int vip_level);
}
