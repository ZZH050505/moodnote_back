package com.example.demo.mapper;

import com.example.demo.pojo.User;
import com.example.demo.pojo.Verification;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;


@Mapper
public interface AuthMapper {

    @Insert("insert into verification_code(account, verification_code) values (#{account},#{code})")
    public void insertVerificationCode(String account,String code);

    @Delete("delete from verification_code where account=#{account}")
    void deleteVerificationCode(String account);


    @Update("update user set LastLoginTime=#{lastLoginTime} where UserID=#{id}")
    void logout(LocalDateTime lastLoginTime,Integer id);

    @Select("select * from verification_code where account= #{account}")
    Verification selectVerificationCode(String account);

    @Update("update user set LastLoginTime= #{lastLoginTime},Status=user.Status-1 where UserID= #{id}")
    void updateLastLoginTimeAndStatus(LocalDateTime lastLoginTime, Integer id);

    @Update("update user set Status=user.Status+1 where Account= #{account}")
    void updateStatus(User user1);
}
