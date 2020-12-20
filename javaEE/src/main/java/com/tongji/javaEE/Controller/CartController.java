package com.tongji.javaEE.Controller;
import java.util.ArrayList;
import java.util.List;
import com.tongji.javaEE.Service.*;
import com.tongji.javaEE.Domain.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/")
public class CartController {
    @Autowired
    CartService cartservice;

    @GetMapping(value="cart/{userId}",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Format getByUserId(@PathVariable("userId") String userId){
        return cartservice.searchCarByUserId(userId);
    }

    @PostMapping(value="cart",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Format insertCart(@RequestBody Cart item){
        System.out.println(item.toString());
        return cartservice.insertCart(item);
    }

    @DeleteMapping(value="cart/{carVin}/{userId}",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Format deleteCart(@PathVariable("carVin") String carVin,@PathVariable("userId") String userId){
        return cartservice.deleteCart(carVin,userId);
    }
}
