package com.tongji.javaEE.Dao;

import com.tongji.javaEE.Domain.News;
import com.tongji.javaEE.Service.VObject.NewsVO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsRepository extends JpaRepository<News,String> {
    //通过id 查询单条新闻
    News findNewsById(String id);
    //分页，条件查询新闻列表
    @Query("select  new com.tongji.javaEE.Service.VObject.NewsVO"+
            "(l.id,l.title,l.content,l.part,l.authorId,l.authorName,l.postDate)"+
            " from News l"+
            " where l.title like %:keyword% or l.content like %:keyword%"
    )
    List<NewsVO> getNewsListByCondition(@Param("keyword")String keyword, Pageable pageable);
    //条件查询计算，新闻列表总数
    @Query("select count(l) from News l where l.title like %:keyword% or l.content like %:keyword%")
    int countByCompanyIdAndStatus(@Param("keyword")String keyword);
    //根据新闻id获取具体信息
    @Query("select  new com.tongji.javaEE.Service.VObject.NewsVO"+
            "(l.id,l.title,l.content,l.part,l.authorId,l.authorName,l.postDate)"+
            " from News l"+
            " where l.id=:news_id "
    )
    NewsVO getNewsById(@Param("news_id")String news_id);

}
