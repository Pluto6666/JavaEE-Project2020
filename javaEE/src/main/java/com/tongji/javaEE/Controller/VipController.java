package com.tongji.javaEE.Controller;

import com.tongji.javaEE.Dao.PermissionRepository;
import com.tongji.javaEE.Dao.UserRepository;
import com.tongji.javaEE.Dao.VipRepository;
import com.tongji.javaEE.Domain.Permission;
import com.tongji.javaEE.Domain.User;
import com.tongji.javaEE.Domain.Vip;
import com.tongji.javaEE.Service.QObject.VipQO.AddVipQO;
import com.tongji.javaEE.Service.QObject.VipQO.SetVipQO;
import com.tongji.javaEE.Service.QObject.pageQO;
import com.tongji.javaEE.Service.VObject.InviVipVO;
import com.tongji.javaEE.Util.ResultUtils;
import com.tongji.javaEE.Util.ReturnMessage;
import com.tongji.javaEE.Util.pageQueryData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/vip")//映射请求路径
public class VipController {
    @Autowired
    private VipRepository vipRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    /**
     * @func 新增会员
     * @author Miracle Ray
     */

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage add( @RequestBody AddVipQO addVipQO ){
        //可以方便地修改日期格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Vip newVip = new Vip();
        ReturnMessage returnMessage = new ReturnMessage(0,"Unknown Error");
        String inUid = addVipQO.vip_id;
        int inLevel = addVipQO.vip_level;

        //判断用户id是否存在，会员id和用户id一致
        User user = userRepository.findByUserId(inUid);
        if(user==null){
            returnMessage.setMessage("用户id不存在，请先注册用户。");
            return returnMessage;
        }
        //用户已经是会员了
        Vip findVip = vipRepository.findByVipId(inUid);
        if(findVip!=null){
            returnMessage.setMessage("用户已是会员。");
            return returnMessage;
        }
        Date date=new Date();
        String beginDate=dateFormat.format(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 30);
        String endDate=dateFormat.format(cal.getTime());

        newVip.setVipId(inUid);
        newVip.setVipLevel(inLevel);
        newVip.setBeginTime(beginDate);
        newVip.setEndTime(endDate);
        //System.out.println(newVip.toString());
        vipRepository.save(newVip);
        returnMessage.setCode(1);
        returnMessage.setMessage("成功设置用户为会员。");
        return returnMessage;
    }

    /**
     * @func 查询单个会员信息
     * @author Ray
     * @param vid
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    @ResponseBody
    public Object getVip(String vid) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //判断用户是否存在
        String user_name = userRepository.findByUserId(vid).getUserName();
        if(user_name==null){
            return ResultUtils.error("用户不存在。");
        }
        //判断用户是否为会员
        Vip vip = vipRepository.findByVipId(vid);
        if(vip==null){
            return ResultUtils.error("用户不为会员，请先设置会员。");
        }
        //判断会员是否过期
        String endDate=vip.getEndTime();
        Date nowDate = new Date();
        Date checkDate = dateFormat.parse(endDate);
        if(checkDate.compareTo(nowDate)<0){
            vipRepository.delete(vip);
            return ResultUtils.error("会员已过期，后台自动删除会员。");
        }
        InviVipVO inviVipVO = new InviVipVO(user_name,vip);
        return ResultUtils.success(inviVipVO,"查询会员成功。");
    }

    /**
     * @func 修改会员信息
     * @author Ray
     * @param setVipQO
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/set",method = RequestMethod.POST)
    @ResponseBody
    public Object set(@RequestBody SetVipQO setVipQO) throws ParseException {
        //可以方便地修改日期格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Vip vip = new Vip();
        vip = vipRepository.findByVipId(setVipQO.vip_id);
        if(vip==null){
            return ResultUtils.error("用户不是会员。");
        }
        Date start_time = dateFormat.parse(vip.getBeginTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(start_time);
        cal.add(Calendar.DATE,setVipQO.surplus);
        String end_time = dateFormat.format(cal.getTime());

        vip.setVipLevel(setVipQO.vip_level);
        vip.setEndTime(end_time);
        vipRepository.save(vip);
        return ResultUtils.success("修改会员信息成功。");
    }

    /**
     * @func 删除会员
     * @param vid
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public Object delete(String vid){
        Vip vip = vipRepository.findByVipId(vid);
        if(vip==null){
            return ResultUtils.error("用户ID不存在");
        }
        vipRepository.deleteById(vid);
        return ResultUtils.success(vip,"会员删除成功。");
    }

    /**
     * @func 分页查询
     * @param pageQO
     * @return
     */
    @RequestMapping(value = "/getPage",method = RequestMethod.POST)
    @ResponseBody
    public Object getPage(@RequestBody pageQO pageQO){
        List<Vip> vipList=vipRepository.findAll();
        List<InviVipVO> vipVOS=new ArrayList<>();

        int num=vipList.size();
        int pageNum=pageQO.page_num;
        int pageSize=pageQO.page_size;

        int start=(pageNum-1)*pageSize;
        if(start>=num){
            return ResultUtils.error("查询超过表上限。");
        }
        int end=((start+pageSize-1)<num)?(start+pageSize):num;
        for(int i=start;i<end;i++){
            Vip tmpVip = vipList.get(i);
            String userName = userRepository.findByUserId(tmpVip.getVipId()).getUserName();
            InviVipVO inviVipVO = new InviVipVO(userName,tmpVip);
            vipVOS.add(inviVipVO);
        }
        pageQueryData<List<InviVipVO>> pageQueryData = new pageQueryData<>();
        pageQueryData.setTotal(num);
        pageQueryData.setPageNum(pageNum);
        pageQueryData.setData(vipVOS);
        return ResultUtils.success(pageQueryData,"第"+pageNum+"页分页信息查询成功，每页共"+pageSize+"条信息。");
    }

    /**
     * @func 查询权限
     */
    @RequestMapping(value = "/permission",method = RequestMethod.GET)
    @ResponseBody
    public Object getPermission(int vip_level){
//       if(vip_level>3){
//           return ResultUtils.error("超出最高等级权限");
//       }
        List<Permission> permissionVOList = permissionRepository.findByVipLevelLessThanEqual(vip_level);
       return ResultUtils.success(permissionVOList,"查询权限成功。");
    }
}
