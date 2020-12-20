package com.tongji.javaEE.Service;

import org.springframework.stereotype.Service;
import com.tongji.javaEE.Domain.*;
@Service
public class ValueService {
    public messageReturn valueReturn(int kilometer){

        messageReturn result = new messageReturn();

        if(kilometer<=1000)
        {
            result.code = 450000;
        }

        if (kilometer > 1000&&kilometer<=10000)
        {
            result.code = 400000;
        }

        if (kilometer > 10000&&kilometer <= 20000)
        {
            result.code = 350000;
        }

        if (kilometer > 20000 && kilometer <= 30000)
        {
            result.code = 330000;
        }
        if (kilometer > 30000 && kilometer <= 40000)
        {
            result.code = 310000;
        }

        if (kilometer > 40000 && kilometer <= 50000)
        {
            result.code = 290000;
        }
        if (kilometer > 50000 && kilometer <= 60000)
        {
            result.code = 240000;
        }
        if (kilometer > 60000 && kilometer <= 70000)
        {
            result.code = 220000;
        }
        if (kilometer > 70000 && kilometer <= 80000)
        {
            result.code = 200000;
        }
        if (kilometer > 80000 && kilometer <= 90000)
        {
            result.code = 170000;
        }
        if (kilometer > 90000 && kilometer <= 100000)
        {
            result.code = 120000;
        }

        if (kilometer > 100000)
        {
            result.code = 100000;
        }

        result.message="已经成功返回汽车估值！";
        return result;
    }
}
