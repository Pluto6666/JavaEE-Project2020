package com.tongji.javaEE.Controller;


import com.tongji.javaEE.Dao.UserRepository;
import com.tongji.javaEE.Util.DataReturnMessage;
import com.tongji.javaEE.Domain.User;
import com.tongji.javaEE.Util.pageQueryData;
import com.tongji.javaEE.Service.QObject.pageQO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/userList")//映射请求路径
public class UserListController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/pageList", method = RequestMethod.POST)
    @ResponseBody
    public DataReturnMessage<pageQueryData> getByPage(@RequestBody pageQO pageQO){

        List<User> userList = userRepository.findAll();
        DataReturnMessage<pageQueryData> dataReturnMessage = new DataReturnMessage<>(0,"Unknown Error");
        pageQueryData<List<User>> pageQueryData = new pageQueryData<>();

        int num = userList.size();
        int pageNum=pageQO.page_num;
        int pageSize=pageQO.page_size;
        List<User> resList = new ArrayList<>();
        int start=(pageNum-1)*pageSize;
        if(start>=num){
            dataReturnMessage.setMessage("查询超过表上限");
            dataReturnMessage.setData(null);
            return dataReturnMessage;
        }
        int end=((start+pageSize-1)<num)?(start+pageSize):num;

        for(int i = start;i<end;i++){
            resList.add(userList.get(i));
        }

        pageQueryData.setPageNum(pageNum);
        pageQueryData.setTotal(num);
        pageQueryData.setData(resList);

        dataReturnMessage.setCode(1);
        dataReturnMessage.setMessage("第"+pageNum+"页分页信息查询成功，每页共"+pageSize+"条信息。");
        dataReturnMessage.setData(pageQueryData);
        return dataReturnMessage;

    }
}
