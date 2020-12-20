package com.tongji.javaEE.Dao;

import com.tongji.javaEE.Domain.PostData;
import com.tongji.javaEE.Service.VObject.PostDataVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostDataRepository extends JpaRepository<PostData,String> {
    PostData findPostDataByPostId(String postId);
    //模糊查询，查找主题帖列表（内容或标题包含关键字）
    @Query("select  new com.tongji.javaEE.Service.VObject.PostDataVO"+
            "(l.postId,l.userId,c.userName,l.postDate,l.title,l.content)"+
            " from  PostData l join User c on l.userId=c.userId " +
            " where l.title like %:keyword% or l.content like %:keyword% "
    )
    List<PostDataVO> getPostListLikeTitleOrContent(@Param("keyword")String keyword);
    //根据帖子id获取帖子具体信息
    @Query("select  new com.tongji.javaEE.Service.VObject.PostDataVO"+
            "(l.postId,l.userId,c.userName,l.postDate,l.title,l.content)"+
            " from  PostData l join User c on l.userId=c.userId " +
            " where l.postId=:post_id "
    )
    PostDataVO getPostDataById(@Param("post_id")String post_id);
}
