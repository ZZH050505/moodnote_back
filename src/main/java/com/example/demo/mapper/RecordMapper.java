package com.example.demo.mapper;

import com.example.demo.pojo.Comment;
import com.example.demo.pojo.LikeRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface RecordMapper {

    @MapKey("EventID")
    List<Map<String, Object>> getEventLikeMap(List<Integer> eventIds);

    @MapKey("EventID")
    List<Map<String, Object>> getEventCommentMap(List<Integer> eventIds);



}
