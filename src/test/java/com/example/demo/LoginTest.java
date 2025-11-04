package com.example.demo;
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
        user.setAccount("a2987025642@163.com");
        user.setPassword("123456");
        user.setRole(1);
        authService.userLogin(user);
    }

}
