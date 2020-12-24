package com.tongji.javaEE.Service;

import com.tongji.javaEE.Dao.*;
import com.tongji.javaEE.Domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private AuditDataRepository auditRepository;
    @Autowired
    private CarDataRepository carDataRepository;
    @Autowired
    private MoneyRepository moneyRepository;

    public Format searchOrderById(String buyerId){
        List<Order_data> list=orderRepository.findByBuyerId(buyerId);

        Format response=new Format<Order_data>();
        if(list.size()==0){
            response.setCode(0);
            response.setMessage("您当前无任何订单");
        }
        else{
            response.setCode(1);
            response.setMessage("成功查询到所有订单");
        }
        response.setData(list);
        return response;
    }

    public Format insertOrder(Order_data order){
        List<carData> list=carDataRepository.findByCarVin(order.getCarVin());
        Format response=new Format<Order_data>();
        System.out.println(list.get(0).getIforder());
        if(list.get(0).getIforder()==0){
            orderRepository.save(order);
            list.get(0).setIforder(1);
            carDataRepository.save(list.get(0));
            List<auditData> list1=auditRepository.findByCarVin(order.getCarVin());
            list1.get(0).setStatus(3);
            auditRepository.save(list1.get(0));
            cartRepository.deleteByCarVin(order.getCarVin());
            response.setCode(1);
            response.setMessage("已成功下单");
        }
        else{
            response.setCode(0);
            response.setMessage("下单失败，该商品已经被下单！");
        }
        response.setData(new ArrayList());
        return response;
    }

    public Format deleteOrder(String carVin){
        List<Order_data> list=orderRepository.findByCarVin(carVin);
        Format response=new Format<Order_data>();
        if(list.size()==0){
            response.setCode(0);
            response.setMessage("订单信息不存在");
            return response;
        }
        if(list.get(0).getStatus()==1){
            response.setCode(0);
            response.setMessage("订单已付款，无法撤回");
            return response;
        }
        orderRepository.deleteByCarVin(carVin);
        List<carData> list1=carDataRepository.findByCarVin(carVin);
        if(list1.size()!=0){
            list1.get(0).setIforder(0);
            carDataRepository.save(list1.get(0));
        }
        List<auditData> list2=auditRepository.findByCarVin(carVin);
        if(list2.size()!=0){
            list2.get(0).setStatus(2);
            auditRepository.save(list2.get(0));
        }
        response.setCode(1);
        response.setMessage("订单撤回成功");
        return response;
    }

    public Format payOrder(Detailorder order){
        int status=orderRepository.findByCarVin(order.getCarVin()).get(0).getStatus();
        List<Moneydata> money=moneyRepository.findByUserId(order.getBuyerId());
        Format response=new Format<Payresult>();
        if(status==1){
            response.setCode(0);
            response.setMessage("用户已经付过款！不能重复付款！");
            List<Payresult> list= new ArrayList<Payresult>();
            Payresult payresult=new Payresult();
            payresult.setStatus(status);
            payresult.setMoney(money.get(0).getMoney());
            list.add(payresult);
            response.setData(list);
            return response;
        }
        if (money.get(0).getMoney()>=order.getPrice() && status==0){
            money.get(0).setMoney(money.get(0).getMoney()-order.getPrice());
            moneyRepository.save(money.get(0));
            List<Moneydata> list1=moneyRepository.findByUserId(order.getSellerId());
            list1.get(0).setMoney(list1.get(0).getMoney()+order.getPrice());
            moneyRepository.save(list1.get(0));
            List<Order_data> list2=orderRepository.findByCarVin(order.getCarVin());
            list2.get(0).setStatus(1);
            orderRepository.save(list2.get(0));
            response.setCode(1);
            response.setMessage("付款成功！交易完成！");
            List<Payresult> list3= new ArrayList<Payresult>();
            Payresult payresult=new Payresult();
            payresult.setStatus(status);
            payresult.setMoney(money.get(0).getMoney()-order.getPrice());
            list3.add(payresult);
            response.setData(list3);
            return response;
        }
        response.setCode(0);
        response.setMessage("付款失败！余额不足！");
        List<Payresult> list4= new ArrayList<Payresult>();
        Payresult payresult=new Payresult();
        payresult.setStatus(status);
        payresult.setMoney(money.get(0).getMoney());
        list4.add(payresult);
        response.setData(list4);
        return response;
    }

    public Format findallorders(){
        List<Order_data> list=orderRepository.findAll();
        Format response=new Format<Order_data>();
        response.setCode(1);
        response.setMessage("查询成功");
        response.setData(list);
        return response;
    }
}
