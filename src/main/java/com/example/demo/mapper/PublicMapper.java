package com.example.demo.mapper;

import com.example.demo.pojo.Comment;
import com.example.demo.pojo.HappyEvent;
import com.example.demo.pojo.LikeRecord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PublicMapper {
    @Select("select * from moodnote.happyevent where IsPublic=1 and Status=1 order by CreateTime")
    List<HappyEvent> selectAllPublic();

    Boolean selectLikeById(Integer userId, Integer eventId);

    @Delete("delete from moodnote.likerecord where EventID=#{eventId}")
    void deleteLikeByEventId(Integer eventId);

    @Delete("delete from moodnote.comment where EventID=#{eventId}")
    void deleteCommentByEventId(Integer eventId);

    @Insert("insert into moodnote.likerecord(EventID, UserID, LikeTime) values (#{eventID},#{userID},#{likeTime})")
    void insertLikeRecord(LikeRecord likeRecord);
    @Delete("delete from moodnote.likerecord where UserID=#{id} and EventID=#{eventId}")
    void deleteLikeRecord(Integer id, Integer eventId);


    void insert(Comment comment);

//    @Select("select * from moodnote.comment where EventID=#{eventId}")
    List<Comment> getEventCommentByEventId(Integer eventId);

    @Delete("delete  from moodnote.comment where CommentID=#{commentId} and UserID=#{userId}")
    void deleteComment(Integer userId, Integer commentId);
}
