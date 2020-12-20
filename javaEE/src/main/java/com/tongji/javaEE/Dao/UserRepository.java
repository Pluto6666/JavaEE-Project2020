package com.tongji.javaEE.Dao;


import com.tongji.javaEE.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String>{
    User findByUserId (String userId);
    User findByUserName(String userName);

}
