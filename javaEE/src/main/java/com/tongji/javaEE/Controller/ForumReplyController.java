package com.tongji.javaEE.Controller;
/**
 * 负责论坛主题回复的 添加 和 删除
 */

import com.tongji.javaEE.Dao.NewsRepository;
import com.tongji.javaEE.Dao.PostDataRepository;
import com.tongji.javaEE.Dao.ReplyDataRepository;
import com.tongji.javaEE.Domain.ReplyData;
import com.tongji.javaEE.Service.QObject.AddForumReplyQO;
import com.tongji.javaEE.Util.ResultUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/forumReply")//映射请求路径
@Api(description = "回复帖管理")
public class ForumReplyController {
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private PostDataRepository postDataRepository;
    @Autowired
    private ReplyDataRepository replyDataRepository;
    //发布回复
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object addForumReply(
            @RequestBody AddForumReplyQO addQO
            ) {
        ReplyData reply=replyDataRepository.findReplyDataByReplyId(addQO.reply_id);
        if(reply==null){
            ReplyData add_post=new ReplyData(
                    addQO.reply_id,
                    addQO.user_id,
                    addQO.post_id,
                    addQO.reply_date,
                    addQO.content
            );
            replyDataRepository.save(add_post);
            return ResultUtils.success("成功发布回复");
        }else{
            return ResultUtils.error("该回复id已存在，创建失败");
        }
    }
    //删除回复
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteNews(
            @RequestParam("reply_id") String reply_id
    ) {
        ReplyData replyData=replyDataRepository.findReplyDataByReplyId(reply_id);
        if(replyData==null){
            return ResultUtils.error("该回复不存在，删除失败");
        }else{
            replyDataRepository.delete(replyData);
            return ResultUtils.success("成功删除回复 ");
        }
    }
}
