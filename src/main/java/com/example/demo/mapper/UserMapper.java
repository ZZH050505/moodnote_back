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

    @Select("select * from user where Account=#{account} and Password=#{password} and Status=0")
    User selectByAccountAndPasswordAndRole0(User user);

    @Select("select * from user where Account=#{account} and Password=#{password} and Status=1")
    User selectByAccountAndPasswordAndRole1(User user);
}
