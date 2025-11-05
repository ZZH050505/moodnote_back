package com.example.demo.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RecordMapper {

    @MapKey("EventID")
    List<Map<String, Object>> getEventLikeMap(List<Integer> eventIds);

    @MapKey("EventID")
    List<Map<String, Object>> getEventCommentMap(List<Integer> eventIds);


    Boolean selectLikeById(Integer userId, Integer eventId);

    @Delete("delete from moodnote.likerecord where EventID=#{eventId}")
    void deleteLikeByEventId(Integer eventId);

    @Delete("delete from moodnote.comment where EventID=#{eventId}")
    void deleteCommentByEventId(Integer eventId);
}
