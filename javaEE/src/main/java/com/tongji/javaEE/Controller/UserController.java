package com.tongji.javaEE.Controller;


import com.tongji.javaEE.Dao.MoneyRepository;
import com.tongji.javaEE.Dao.UserRepository;
import com.tongji.javaEE.Domain.Moneydata;
import com.tongji.javaEE.Util.DataReturnMessage;
import com.tongji.javaEE.Util.ReturnMessage;
import com.tongji.javaEE.Domain.User;
import com.tongji.javaEE.Service.QObject.loginQO;
import com.tongji.javaEE.Service.QObject.registerQO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")//映射请求路径
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MoneyRepository moneyRepository;

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public List<User> getAll(
    ) {
        List<User> user = userRepository.findAll();
        return user;
    }

    /**
     * @func 用户注册
     * @author Miracle Ray
     * */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage Register(
            @RequestBody registerQO regQO
    ){
        User user;
            ReturnMessage result = new ReturnMessage(0,"Unknown Error");

            //判断用户ID是否重复
            user = userRepository.findByUserId(regQO.user_id);
            if(user!=null) {
                result.setMessage("用户ID已存在！");
                return result;
        }
        //判断用户名是否重复
        user = userRepository.findByUserName(regQO.user_name);
        if(user!=null){
            result.setMessage("用户名已存在！");
            return result;
        }


        User reg_user=new User(regQO);
        userRepository.save(reg_user);
        result.setCode(1);
        result.setMessage("注册成功");

        //分配资金
        Moneydata newMoney = new Moneydata();
        newMoney.setUserId(regQO.user_id);
        newMoney.setMoney(1000000);
        moneyRepository.save(newMoney);

        return result;
    }

    /**
     * @func 用户登录
     * @author Miracle Ray
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage login(
            @RequestBody loginQO loginQO
    ){
        User user;
        //code: 0 未知错误 1 登录成功 2 密码错误 3 用户未注册
        ReturnMessage returnMessage = new ReturnMessage(0,"Unknown Error");

        //检测用户ID是否存在
        user = userRepository.findByUserId(loginQO.user_id);
        if(user==null){
            returnMessage.setCode(3);
            returnMessage.setMessage("用户未注册");
            return returnMessage;
        }

        //用户ID存在：密码是否正确
        if(loginQO.password.equals(user.getPassword())){
           returnMessage.setCode(1);
           returnMessage.setMessage("登录成功");
        }
        else if(!loginQO.password.equals(user.getPassword())){
            returnMessage.setCode(2);
            returnMessage.setMessage("密码错误");
        }

        return returnMessage;
    }

    /**
     * @func 根据ID查询用户
     * @author Miracle Ray
     */
    @RequestMapping(value = "/userID",method = RequestMethod.GET)
    @ResponseBody
    public DataReturnMessage<User> getByUserID(String user_id){
        User user;
        //code： 0 失败 1成功
        DataReturnMessage<User> dataReturnMessage = new DataReturnMessage<>(0, "Unknown Error");

        //检测用户ID是否存在
        user = userRepository.findByUserId(user_id);
        if(user==null){
            dataReturnMessage.setMessage("用户ID不存在");
            dataReturnMessage.setData(null);
            return dataReturnMessage;
        }
        else{
            dataReturnMessage.setCode(1);
            dataReturnMessage.setMessage("查询成功");
            dataReturnMessage.setData(user);
        }
        return dataReturnMessage;
    }

    /**
     * @func 修改用户信息
     * @author Miracle Ray
     */
    @RequestMapping(value = "/updateInfo",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage changeUserInfo(@RequestBody registerQO newQO){
        ReturnMessage returnMessage = new ReturnMessage(0,"Unknown Error");
        String inUid=newQO.user_id;
        String inPassword=newQO.password;

        //用户ID不存在
        User user = userRepository.findByUserId(inUid);
        if(user==null){
            returnMessage.setMessage("用户ID不存在");
            return returnMessage;
        }

        //用户昵称重复
        User tmpUser = userRepository.findByUserName(newQO.user_name);
        if(tmpUser!=null){
            if(!tmpUser.getUserId().equals(inUid)){
                returnMessage.setMessage("用户昵称重复");
                return returnMessage;
            }
        }

        //修改成功
        User newUser = new User(newQO);
        userRepository.save(newUser);
        returnMessage.setCode(1);
        returnMessage.setMessage("修改成功");
        return returnMessage;
    }

    /**
     * @func 删除单个用户信息
     * @author Miracle Ray
     */
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public DataReturnMessage<User> delete(String user_id){
        DataReturnMessage<User> dataReturnMessage = new DataReturnMessage<>(0,"Unknown Error");
        User oldUser = new User();

        oldUser = userRepository.findByUserId(user_id);
        if(oldUser==null){
            dataReturnMessage.setMessage("用户ID不存在");
            dataReturnMessage.setData(null);
            return dataReturnMessage;
        }
        userRepository.deleteById(user_id);
        dataReturnMessage.setCode(1);
        dataReturnMessage.setMessage("删除成功");
        dataReturnMessage.setData(oldUser);
        return dataReturnMessage;
    }
}
