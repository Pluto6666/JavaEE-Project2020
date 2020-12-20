package com.tongji.javaEE.Controller;
/**
 * 负责论坛主题帖的 发布 和 删除
 */

import com.tongji.javaEE.Dao.NewsRepository;
import com.tongji.javaEE.Dao.PostDataRepository;
import com.tongji.javaEE.Domain.PostData;
import com.tongji.javaEE.Service.QObject.AddForumPostQO;
import com.tongji.javaEE.Util.ResultUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/forumPost")//映射请求路径
@Api(description = "主题帖管理")
public class ForumPostController {
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private PostDataRepository postDataRepository;
    //发布主题帖
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object addForumPost(
            @RequestBody AddForumPostQO addQO
            ) {
        PostData post=postDataRepository.findPostDataByPostId(addQO.post_id);
        if(post==null){
            PostData add_post=new PostData(
                    addQO.post_id,
                    addQO.user_id,
                    addQO.title,
                    addQO.post_date,
                    addQO.content
            );
            postDataRepository.save(add_post);
            return ResultUtils.success("成功发布主题帖");
        }else{
            return ResultUtils.error("该帖子id已存在，创建失败");
        }
    }
    //删除主题帖新闻
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteNews(
            @RequestParam("post_id") String post_id
    ) {
        PostData postData=postDataRepository.findPostDataByPostId(post_id);
        if(postData==null){
            return ResultUtils.error("该主题帖不存在，删除失败");
        }else{
            postDataRepository.delete(postData);
            return ResultUtils.success("成功删除主题帖 ");
        }
    }
}
