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
public class MoneyController {
    @Autowired
    private MoneyService moneyService;

    @GetMapping(value="money/{userId}",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Format getByUserId(@PathVariable("userId") String userId){
        return moneyService.searchMoneyById(userId);
    }
}
