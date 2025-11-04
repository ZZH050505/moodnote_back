package com.example.demo.mapper;

import com.example.demo.pojo.Auth;
import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {


    @Select("select * from user where Account=#{account}")
    public  User selectByAccount(String account);


    void userInsert(User user);

    @Select("select * from user where Account=#{account} and Password=#{password} and #{role}=0")
    User selectByAccountAndPasswordAndRole0(User user);

    @Select("select * from user where Account=#{account} and Password=#{password} and #{role}=1")
    User selectByAccountAndPasswordAndRole1(User user);

//    @Select("select * from user where UserID= #{id}")
    User selectById(Integer id);

    @Insert("update user set Nickname=#{nickname} where UserId=#{id}")
    void updateProfile(Integer id, String nickname);
}
