package com.tongji.javaEE.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tongji.javaEE.Service.*;
import com.tongji.javaEE.Domain.*;
@RestController
@RequestMapping("api/value")
public class ValueController {
    @Autowired
    ValueService valueService = new ValueService();
    @RequestMapping(value = "/{kilometers}",method = RequestMethod.GET)
    public messageReturn valueGet(@PathVariable(value = "kilometers") int kilometers){
        return valueService.valueReturn(kilometers);
    }

}
