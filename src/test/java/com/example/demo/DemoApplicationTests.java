package com.example.demo;

import com.example.demo.mapper.AuthMapper;
import com.example.demo.pojo.Auth;
import com.example.demo.utils.SendMail;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.LocalDateAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@Slf4j
@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private AuthMapper autoMapper;

    @Test
    public void getCode(Auth auth) {
        //大概流程：
        //随机生成一个验证码
        //作为密钥创建jwt令牌
        //jwt令牌发送给用户
        //验证码发送给邮箱
        //用户输入验证码
        //在点击注册按钮的时候验证验证码
        //若可以，则创建用户
        //若错误，则提示验证码错误
        String code = SendMail.generateRandomLetters();
        String account = auth.getAccount();
        //若邮箱对应的验证码存在，则删除
        autoMapper.deleteVerificationCode(account);
        //将验证码和邮箱保存到数据库中
        autoMapper.insertVerificationCode(account,code);
        log.info("邮箱,验证码:{},{}",account,code);
        //发送验证码
        SendMail.send(auth.getAccount(),code);
        return;
    }

    @Test
    void contextLoads()
    {
        log.info("测试");
        Auth auth = new Auth();
        auth.setAccount("a2987025642@163.com");
        getCode(auth);
    }

    @Test
    void test()
    {
        log.info("测试");
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
    }
}
