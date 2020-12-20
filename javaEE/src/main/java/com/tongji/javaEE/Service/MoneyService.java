package com.tongji.javaEE.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import com.tongji.javaEE.Dao.*;
import com.tongji.javaEE.Domain.*;
@Service
public class MoneyService {
    @Autowired
    private MoneyRepository moneyRepository;

    public Format searchMoneyById(String userId){
        List<Moneydata> list=moneyRepository.findByUserId(userId);

        Format response=new Format<Buying_leads>();
        if(list.size()==0){
            response.setCode(0);
            response.setMessage("未查询到您的个人账户");
        }
        else{
            response.setCode(1);
            response.setMessage("成功查询到账户余额");
        }
        response.setData(list);
        return response;
    }
}
