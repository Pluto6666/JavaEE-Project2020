package com.tongji.javaEE.Controller;
/**
 * 新闻模块相关接口，增、删、查
 */
import com.tongji.javaEE.Dao.NewsRepository;
import com.tongji.javaEE.Domain.News;
import com.tongji.javaEE.Service.QObject.NewsQO.AddNewsQO;
import com.tongji.javaEE.Service.QObject.NewsQO.QueryNewsListQO;
import com.tongji.javaEE.Service.VObject.NewsPageVO;
import com.tongji.javaEE.Service.VObject.NewsVO;
import com.tongji.javaEE.Util.ResultUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")//映射请求路径
@Api(description="新闻管理")
public class NewsController {
    @Autowired
    private NewsRepository newsRepository;
    //添加新闻
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object addNews(
            @RequestBody AddNewsQO addQO
            ) {
        News news= newsRepository.findNewsById(addQO.news_id);
        if(news==null){
            News news_add=new News(
                    addQO.news_id,
                    addQO.title,
                    addQO.content,
                    addQO.part,
                    addQO.author_id,
                    addQO.author_name,
                    addQO.post_date
            );
            newsRepository.save(news_add);
            return ResultUtils.success(news_add);
            //return ResultUtils.success("成功发布新闻");
        }else{
            return ResultUtils.error("该新闻id已存在，创建失败");
        }
    }
    //删除新闻
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteNews(
            @RequestParam("news_id") String news_id
    ) {
        News news= newsRepository.findNewsById(news_id);
        if(news==null){
            return ResultUtils.error("未找到该新闻，删除失败");
        }else{
            newsRepository.delete(news);//删除
            return ResultUtils.success("成功删除新闻");
        }
    }
    //根据新闻id，查找具体一条新闻
    @RequestMapping(value = "/getNews", method = RequestMethod.POST)
    @ResponseBody
    public Object getNews(
            @RequestParam("news_id") String news_id
    ) {
        News test= newsRepository.findNewsById(news_id);
        if(test==null){
            return ResultUtils.error("新闻不存在，查找失败");
        }else{
            NewsVO news=newsRepository.getNewsById(news_id);
            return ResultUtils.success(news,"查找成功");
        }
    }
    //分页，条件查询新闻数据列表
    @RequestMapping(value = "/get_pageNews", method = RequestMethod.POST)
    @ResponseBody
    public Object get_pageNews(
            @RequestBody QueryNewsListQO queryQO
    ) {
        List<NewsVO> list=newsRepository.getNewsListByCondition(queryQO.keyword,PageRequest.of(queryQO.page_num, queryQO.page_size));
        int total=newsRepository.countByCompanyIdAndStatus(queryQO.keyword);
        NewsPageVO news=new NewsPageVO(total,queryQO.page_num,list);
        return ResultUtils.success(news,"获取成功");
    }
}
