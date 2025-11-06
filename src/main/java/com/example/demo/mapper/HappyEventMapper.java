package com.example.demo.mapper;

import com.example.demo.pojo.HappyEvent;
import com.example.demo.service.HappyEventService;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public interface HappyEventMapper {



    void insert(HappyEvent happyEvent);

    @Select("select * from moodnote.happyevent where UserID=#{id} order by CreateTime")
    List<HappyEvent> selectById(Integer id);


    @Select("select * from moodnote.happyevent  where EventID=#{id}")
    HappyEvent selectByEventId(Integer id);

//    @Update("update happyevent set =#{} where EventID=#{eventID}")
    void updateEvent(HappyEvent event);

    @Delete("delete from moodnote.happyevent where EventID= #{eventId}")
    void deleteByEventId(Integer eventId);


}
