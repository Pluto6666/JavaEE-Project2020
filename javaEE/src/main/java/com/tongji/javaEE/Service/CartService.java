package com.tongji.javaEE.Service;

import com.tongji.javaEE.Dao.*;
import com.tongji.javaEE.Domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public CartService(){super();}

    public Format searchCarByUserId(String userId){
        List<Mycart> list=cartRepository.findCarDataByUserId(userId);

        Format response=new Format<Mycart>();
        if(list.size()==0){
            response.setCode(0);
            response.setMessage("您的购物车为空");
        }
        else{
            response.setCode(1);
            response.setMessage("成功查询到结果");
        }
        response.setData(list);
        return response;
    }

    public Format insertCart(Cart entity){
        List tmp=cartRepository.findCartByCarVinEqualsAndUserIdEquals(entity.getCarVin(), entity.getUserId());
        Format response=new Format<Buying_leads>();
        if(tmp.size()==0){
            Cart res=cartRepository.save(entity);
            System.out.println(res.toString());
            response.setCode(1);
            response.setMessage("成功加入购物车");
        }
        else{
            response.setCode(0);
            response.setMessage("购物车中已存在该商品");
        }
        response.setData(new ArrayList());
        return response;
    }

    public Format deleteCart(String carVin,String userId){
        cartRepository.deleteByCarVinAndUserId(carVin,userId);
        Format response=new Format<Buying_leads>();
        response.setCode(1);
        response.setMessage("删除成功");
        response.setData(new ArrayList());
        return response;
    }
}
