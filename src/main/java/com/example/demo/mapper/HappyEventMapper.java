package com.example.demo.mapper;

import com.example.demo.pojo.HappyEvent;
import com.example.demo.service.HappyEventService;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public interface HappyEventMapper {



    void insert(HappyEvent happyEvent);

    @Select("select * from happyevent where UserID=#{id}")
    List<HappyEvent> selectById(Integer id);


    @Select("select * from happyevent  where EventID=#{id}")
    HappyEvent selectByEventId(Integer id);

//    @Update("update happyevent set =#{} where EventID=#{eventID}")
    void updateEvent(HappyEvent event);

    @Delete("delete from happyevent where EventID= #{eventId}")
    void deleteByEventId(Integer eventId);
}
