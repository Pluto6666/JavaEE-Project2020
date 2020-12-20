package com.tongji.javaEE.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.tongji.javaEE.Service.*;
import com.tongji.javaEE.Domain.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping(value="MyInformation/myorder/{userId}",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Format getByUserId(@PathVariable("userId") String userId){
        return orderService.searchOrderById(userId);
    }

    @PostMapping(value="order",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Format insertCart(@RequestBody Order_data item){
        System.out.println(item.toString());
        item.setOrderId(UUID.randomUUID().toString().replaceAll("-", ""));
        item.setStatus(0);
        return orderService.insertOrder(item);
    }

    @DeleteMapping(value="order/{carVin}",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Format deleteByBuyId(@PathVariable("carVin") String carVin){
        return orderService.deleteOrder(carVin);
    }

    @PostMapping(value="money",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Format insertCart(@RequestBody Detailorder detailorder){
        System.out.println(detailorder.toString());
        return orderService.payOrder(detailorder);
    }
}
