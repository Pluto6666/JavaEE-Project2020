package com.tongji.javaEE.Controller;


import com.tongji.javaEE.Domain.auditData;
import com.tongji.javaEE.Domain.dataReturn;
import com.tongji.javaEE.Domain.messageReturn;
import com.tongji.javaEE.Service.AuditDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/Audit")//映射请求路径
public class AuditDataController {

    @Autowired
    private AuditDataService auditDataService;
    @RequestMapping(value = "/{carVin}", method = RequestMethod.GET)
    public dataReturn getAuditByVin(@PathVariable("carVin") String carVin){

        return  auditDataService.findByVin(carVin);
    };

    //获取所有的审核信息
    @RequestMapping(method = RequestMethod.GET)
    public dataReturn getAuditAll(){
        return  auditDataService.findAll();
    };

    //增添审核信息
    @RequestMapping(method = RequestMethod.POST)
    public messageReturn addAudit(@RequestBody auditData audit){
        return auditDataService.addAudit(audit);
    };
    //进行审核
    @RequestMapping(value = "/notaudit",method = RequestMethod.POST)
    public messageReturn doAudit(@RequestBody auditData audit,@RequestParam(value = "ifPass") int ifPass){
        return auditDataService.doAudit(audit,ifPass);
    };
    @RequestMapping(value = "/notaudit1",method = RequestMethod.POST)
    public auditData test(@RequestBody auditData audit){
        return auditDataService.test(audit);
    };
    @RequestMapping(value = "/carsell/{userId}",method = RequestMethod.GET)
    public dataReturn findCarSellBuyUser(@PathVariable(value = "userId") String userId){
        return auditDataService.findSellByUser(userId);
    }
    @RequestMapping(value = "/recall/{carVin}",method = RequestMethod.DELETE)
    public messageReturn recallAudit(@PathVariable(value = "carVin") String carVin){
        return auditDataService.recallAudit(carVin);
    }
}
