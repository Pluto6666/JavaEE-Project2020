package com.tongji.javaEE.Dao;


import com.tongji.javaEE.Domain.PostData;
import com.tongji.javaEE.Domain.ReplyData;
import com.tongji.javaEE.Service.VObject.PostDataVO;
import com.tongji.javaEE.Service.VObject.ReplyDataVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyDataRepository extends JpaRepository<ReplyData,String> {
    ReplyData findReplyDataByReplyId(String replyId);
    //根据帖子id获取所有回复
    @Query("select  new com.tongji.javaEE.Service.VObject.ReplyDataVO"+
            "(l.replyId,l.userId,c.userName,l.replyDate,l.content)"+
            " from  ReplyData l join User c on l.userId=c.userId " +
            " where l.postId=:post_id "+
            " order by l.replyDate desc "
    )
    List<ReplyDataVO> getReplyListById(@Param("post_id")String post_id);
}
