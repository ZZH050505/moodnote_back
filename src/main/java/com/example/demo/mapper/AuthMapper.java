package com.example.demo.mapper;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.*;


@Mapper
public interface AuthMapper {

    @Insert("insert into verification_code(email, verification_code) values (#{email},#{code})")
    public void insertVerificationCode(String email,String code);

    @Delete("delete from verification_code where email=#{email}")
    void deleteVerificationCode(String email);


    @Update("update user set LastLoginTime=#{lastLoginTime} where Account=#{account}")
    void logout(User user);
}
