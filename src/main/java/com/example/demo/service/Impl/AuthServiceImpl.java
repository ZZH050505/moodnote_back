package com.example.demo.service.Impl;

import com.example.demo.mapper.AuthMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.AuthService;
import com.example.demo.utils.SendMail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthMapper authMapper;
    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    public void register(User user) {
        log.info("用户注册:{}",user);
        //查询账号是否已经存在
        //若存在，就提示已经存在
        //创建一个异常，抛出这个自定义异常
        //从user表中查询用户信息
        User user1 =userMapper.selectByAccount(user.getAccount());
        if(user1!=null)
        {
            //抛出已存在异常
            return ;
        }
        //若不存在，就创建用户，并且将令牌删除掉
        //创建用户
        user.setRegisterTime(LocalDateTime.now());
        user.setNickname(SendMail.generateRandomLetters());
        user.setRole(0);
        //默认头像
        user.setAvatarURL("");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setStatus(1);

        userMapper.userInsert(user);
        //删除令牌
        authMapper.deleteVerificationCode(user.getAccount());

    }

    @Transactional
    @Override
    public void sendCode(User user) {
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
        String account = user.getAccount();
        //若邮箱对应的验证码存在，则删除
        authMapper.deleteVerificationCode(account);
        //将验证码和邮箱保存到数据库中
        authMapper.insertVerificationCode(account,code);
        log.info("邮箱,验证码:{},{}",account,code);
        //发送验证码
        SendMail.send(user.getAccount(),code);

    }

    @Override
    public void userLogin(User user) {
        log.info("用户登录:{}",user);
        User user1=userMapper.selectByAccountAndPasswordAndRole0(user);
        if (user1==null)
        {
            //抛出用户不存在异常
            return ;
        }
        log.info("用户登录成功:{}",user1);

    }

    @Override
    public void adminLogin(User user) {
        log.info("管理员登录:{}",user);
        User user1=userMapper.selectByAccountAndPasswordAndRole1(user);
        if (user1==null)
        {
            //抛出用户不存在异常
            return ;
        }
        log.info("用户登录成功:{}",user1);
    }

    @Override
    public void logout(User user) {
        log.info("用户登出:{}",user);
        authMapper.deleteVerificationCode(user.getAccount());
        authMapper.logout(user);
    }


}
