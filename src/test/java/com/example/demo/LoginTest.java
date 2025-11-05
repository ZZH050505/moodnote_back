package com.example.demo;
import com.example.demo.pojo.LoginInfo;
import com.example.demo.pojo.User;
import com.example.demo.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@SpringBootTest
public class LoginTest {


    @Autowired
    private AuthService authService;
    @Test
    public void usrLogin() {
        User user = new User();
//        user.setAccount("a2987025642@163.com");
//        user.setPassword("123456");
        user.setAccount("traveler_li@email.com");
        user.setPassword("8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92");
        //0是用户，1是管理员
        user.setRole(0);
        LoginInfo loginInfo = authService.userLogin(user);
        log.info("用户登录成功:{}",loginInfo);
    }
//traveler_li@email.com
//8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92
//eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjowLCJpZCI6NSwiZXhwIjoxNzYyMzY1MzE3fQ.nzk4DDv3hxDT9kK9YCsbTYRqVj-SnoUXbI4OHFh6Zwo


    //eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoxLCJpZCI6OCwiZXhwIjoxNzYyMzEyMDcwfQ.AlmA_LpS8BQsq9to9HgLk2DtTu8oNBdBd_Wr4Qk31ks
    @Test
    public void adminLogin() {
        User user = new User();
        user.setAccount("a2987025642@163.com");
        user.setPassword("123456");
        user.setRole(1);
        LoginInfo loginInfo = authService.adminLogin(user);
        log.info("用户登录成功:{}",loginInfo);
    }

}
