package com.example.demo.mapper;

import com.example.demo.pojo.EventImage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EventImageMapper {



    void insertBatch(List<EventImage> images) ;

    @Delete("delete from moodnote.eventimage where EventID=#{eventID}")
    void deleteByEventId(Integer eventID);
}
