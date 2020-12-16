package com.example.demo.Model;

import com.example.demo.qO.registerQO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Miracle Ray
 */
@Getter
@Setter
@Entity
@Table(name = "user_data")
public class User implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    //@GeneratedValue
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "city")
    private String city;
    @Column(name = "password")
    private String password;

    public User(){
        super();
    }

    public User(String id, String password){
        super();
        this.userId=id;
        this.password=password;
    }

    public User(registerQO registerQO){
        this.setUserId(registerQO.user_id);
        this.setUserName(registerQO.user_name);
        this.setGender(registerQO.gender);
        this.setCity(registerQO.city);
        this.setPassword(registerQO.password);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userid) {
        this.userId = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
