package com.tongji.javaEE.Controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import com.tongji.javaEE.Service.*;
import com.tongji.javaEE.Domain.*;

@RestController
@RequestMapping("api/")
public class BuyingController  {
    @Autowired
    private BuyingService buyingService;

    @GetMapping(value="MyInformation/mybuy/{userId}",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Format getByUserId(@PathVariable("userId") String userId){
        return buyingService.searchBuyingById(userId);
    }

    @PostMapping(value="carSearch/getBuy",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Format getByCarName(@Param("searchkey") String searchkey){
        return buyingService.searchBuyingByName(searchkey);
    }

    @DeleteMapping(value="MyInformation/mybuy/{buyId}",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Format deleteByBuyId(@PathVariable("buyId") String buyId){
        return buyingService.deleteBuying(buyId);
    }

    @PostMapping(value="carbuy",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Format insertBuying(@RequestBody Buying_leads buy){
        //System.out.println(buy.toString());
        return buyingService.insertBuying(buy);
    }
}
