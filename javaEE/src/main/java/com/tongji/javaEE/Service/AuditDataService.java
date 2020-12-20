package com.tongji.javaEE.Service;


import com.tongji.javaEE.Dao.AuditDataRepository;
import com.tongji.javaEE.Dao.CarDataRepository;
import com.tongji.javaEE.Dao.CarVinDataRepository;
import com.tongji.javaEE.Domain.auditData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tongji.javaEE.Domain.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuditDataService {
    @Autowired
    private AuditDataRepository auditDataRepository;
    @Autowired
    private CarDataRepository carDataRepository;
    @Autowired
    private CarVinDataRepository carVinDataRepository;

    //查找所有审核信息
    public dataReturn findAll(){
        dataReturn result = new dataReturn();
        if(!auditDataRepository.findAll().isEmpty()){
            result.code=1;
            result.message="成功返回数据！";
            result.data = auditDataRepository.findAll();
        }
        else {
            result.code=1;
            result.message="数据为空！";
        }
        return result;
    }

    //根据车架号查找审核信息
    public dataReturn findByVin(String car_vin){
        dataReturn result = new dataReturn();
        List<auditData> myList = new ArrayList();
        if(!auditDataRepository.findById(car_vin).isEmpty()){
            result.code=1;
            result.message="成功返回数据！";
            myList.add(auditDataRepository.findById(car_vin).get());
            result.data = myList;
        }
        else {
            result.code=1;
            result.message="数据为空！";
        }
        return result;
    }

    //添加出售信息
    public messageReturn addAudit(auditData audit){
        messageReturn result = new messageReturn();
        auditData newAudit ;
        if(auditDataRepository.findById(audit.getCarVin()).isEmpty()){
            newAudit = audit;
            audit.setStatus(0);
            auditDataRepository.save(newAudit);
            result.code=1;
            result.message="成功保存数据！";
        }
        else {
            result.code=0;
            result.message="信息重复！";
        }
        return result;
    }

    //进行审核
    public messageReturn doAudit(auditData audit,int ifPass){
        messageReturn result = new messageReturn();
        if(audit.getStatus()!=0){
            result.message="该二手车出售信息已被审核！不能重复审核！";
            result.code=0;
        }
        else {
            //为0审核不通过 为1审核通过
            if(ifPass==0){
                auditData aD= auditDataRepository.findById(audit.getCarVin()).get();
                aD.setStatus(1);
                auditDataRepository.save(aD);
                result.code=1;
                result.message="审核成功！不通过！";
            }
            if(ifPass==1){
                auditData aD= auditDataRepository.findById(audit.getCarVin()).get();
                aD.setStatus(2);
                auditDataRepository.save(aD);

                carData newCar = new carData();
                carVinData newCarVin = new carVinData();

                newCar.setCarVin(audit.getCarVin());
                newCar.setCarCondition(audit.getCarCondition());
                newCar.setAccident(audit.getAccident());
                newCar.setDateBuyin(audit.getDateBuyin());
                newCar.setPhoneNumber(audit.getPhone());
                newCar.setKilometer(audit.getKilometer());
                newCar.setPrice(audit.getPrice());
                newCar.setIforder(0);
                newCar.setUserId(audit.getUserId());
                carDataRepository.save(newCar);

                newCarVin.setCarVin(audit.getCarVin());
                newCarVin.setBrand(audit.getBrand());
                newCarVin.setCarName(audit.getCarName());
                newCarVin.setColor(audit.getColor());
                newCarVin.setDisplacement(audit.getDisplacement());
                newCarVin.setDateProduce(audit.getDate_produce());
                carVinDataRepository.save(newCarVin);

                result.code=1;
                result.message="审核成功！通过！";
            }
        }
        return result;
    }

    //测试接口
    public auditData test(auditData audit){

        return audit;
    }

    //根据用户找出售信息
    public dataReturn findSellByUser(String userId){
        dataReturn result = new dataReturn();
        List<auditData> aD = auditDataRepository.findAllByUserId(userId);

        if(!aD.isEmpty()){
            result.data=aD;
            result.code=1;
            result.message="已经返回该用户的所有出售信息！";
        }
        else {
            result.code=0;
            result.message="该用户没有任何出售信息！";
        }

        return result;
    }

    //撤回出售信息 如果已经审核就不能撤回
    public messageReturn recallAudit(String carVin){
        messageReturn result = new messageReturn();
        auditData aD = auditDataRepository.findById(carVin).get();
        if(aD.getStatus()!=0){
            result.code=0;
            result.message="该出售信息已经经过审核，不能撤回！";
        }
        else {
            auditDataRepository.deleteById(aD.getCarVin());
            result.message="出售信息已经成功撤回！";
            result.code=1;
        }
        return result;
    }

}
