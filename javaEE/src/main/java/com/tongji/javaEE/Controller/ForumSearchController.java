package com.tongji.javaEE.Controller;

import com.tongji.javaEE.Dao.PostDataRepository;
import com.tongji.javaEE.Dao.ReplyDataRepository;
import com.tongji.javaEE.Domain.PostData;
import com.tongji.javaEE.Service.QObject.QueryForumQO;
import com.tongji.javaEE.Service.VObject.PostDataVO;
import com.tongji.javaEE.Service.VObject.ReplyDataVO;
import com.tongji.javaEE.Util.ResultUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forumSearch")//映射请求路径
@Api(description = "论坛查找管理")
public class ForumSearchController {
    @Autowired
    private PostDataRepository postDataRepository;
    @Autowired
    private ReplyDataRepository replyDataRepository;
    //查找帖子
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public Object getForumList(
            @RequestBody QueryForumQO queryQO
    ){
        List<PostDataVO> list=postDataRepository.getPostListLikeTitleOrContent(queryQO.keyword);
        return ResultUtils.success(list,"查找成功");
    }
    //根据帖子id获取帖子的具体信息
    @RequestMapping(value = "/getPost", method = RequestMethod.POST)
    @ResponseBody
    public Object getNews(
            @RequestParam("post_id") String post_id
    ) {
        PostData postData=postDataRepository.findPostDataByPostId(post_id);
        if(postData==null){
            return ResultUtils.error("帖子不存在，查找失败");
        }else{
            PostDataVO post=postDataRepository.getPostDataById(post_id);
            return ResultUtils.success(post,"查找成功");
        }
    }
    //根据帖子id获取所有的回复帖信息
    @RequestMapping(value = "/getReplys", method = RequestMethod.POST)
    @ResponseBody
    public Object getReplys(
            @RequestParam("post_id") String post_id
    ){
        List<ReplyDataVO> list=replyDataRepository.getReplyListById(post_id);
        return  ResultUtils.success(list,"查找成功");
    }

}
