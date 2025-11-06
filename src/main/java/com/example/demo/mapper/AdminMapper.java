package com.example.demo.mapper;

import com.example.demo.pojo.EventQueryParam;
import com.example.demo.pojo.HappyEvent;
import com.example.demo.pojo.User;
import com.example.demo.pojo.UserQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {



    public List<User>  selectAllUserByParam(UserQueryParam param);

    @Update("update moodnote.user set Password=#{password} where UserID=#{userId}")
    void updateUserPassword(Integer userId, String password);

    @Update("update moodnote.happyevent set Status=#{status} where EventID=#{eventId}")
    void updateEventStatus(Integer eventId, Integer status);

    @Update("update moodnote.eventimage set Status= #{status} where ImageID= #{imageId}")
    void updateImageStatus(Integer imageId, String status);


    void updateUserImageStatus(Integer userId, Integer status);


    List<HappyEvent> selectEventByParam(EventQueryParam param);
}
