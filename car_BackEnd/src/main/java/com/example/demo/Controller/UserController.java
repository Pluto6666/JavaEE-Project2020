package com.example.demo.Controller;

import com.example.demo.DAO.UserRepository;
import com.example.demo.Model.ReturnMessage;
import com.example.demo.Model.User;
import com.example.demo.qO.loginQO;
import com.example.demo.qO.registerQO;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")//映射请求路径
public class UserController {
    @Autowired
    private UserRepository userRepository;

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
}
