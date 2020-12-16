package com.example.demo.DAO;

import com.example.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String>{
    User findByUserId (String userId);
    User findByUserName(String userName);
}
