package com.tongji.javaEE.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tongji.javaEE.Dao.*;
import com.tongji.javaEE.Domain.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class BuyingService {
    @Autowired
    private BuyingRepository buyingRepository;

    public Format searchBuyingById(String userId){
        List<Buying_leads> list=buyingRepository.findByUserId(userId);

        Format response=new Format<Buying_leads>();
        if(list.size()==0){
            response.setCode(0);
            response.setMessage("您尚未发布求购信息");
        }
        else{
            response.setCode(1);
            response.setMessage("成功查询到求购信息");
        }
        response.setData(list);
        return response;
    }

    public Format searchBuyingByName(String carName){
        List<Buying_leads> list=buyingRepository.findByCarName(carName);

        Format response=new Format<Buying_leads>();
        if(list.size()==0){
            response.setCode(0);
            response.setMessage("尚未发布新求购信息");
        }
        else{
            response.setCode(1);
            response.setMessage("成功查询到求购信息");
        }
        response.setData(list);
        return response;
    }

    public Format deleteBuying(String buyId){
        int res= buyingRepository.deleteByBuyId(buyId);
        Format response=new Format<Buying_leads>();
        response.setCode(res);
        response.setMessage("删除成功");
        response.setData(new ArrayList());
        return response;
    }

    public Format insertBuying(Buying_leads entity){
        Buying_leads res=buyingRepository.save(entity);
        Format response=new Format<Buying_leads>();
        System.out.println(res.toString());
        response.setCode(1);
        response.setMessage("成功发布求购信息");
        response.setData(new ArrayList());
        return response;
    }
}
