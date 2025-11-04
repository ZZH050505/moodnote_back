package com.example.demo.service.Impl;

import com.example.demo.mapper.AuthMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.Auth;
import com.example.demo.pojo.LoginInfo;
import com.example.demo.pojo.User;
import com.example.demo.pojo.Verification;
import com.example.demo.service.AuthService;
import com.example.demo.utils.JwtUtils;
import com.example.demo.utils.SendMail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthMapper authMapper;
    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    public void register(Auth auth) {
        log.info("用户注册:{}",auth);
        //查询账号是否已经存在
        //若存在，就提示已经存在
        //创建一个异常，抛出这个自定义异常
        //从user表中查询用户信息
        //检验验证码是否存在
        String account = auth.getAccount();
        Verification verification = authMapper.selectVerificationCode(account);
        if(verification==null)
        {
            log.info("验证码不存在");
            //抛出验证码不存在异常
            return ;
        }
        else if(!verification.getVerificationCode().equals(auth.getVerificationCode()))
        {
            log.info("验证码错误");
            //抛出验证码错误异常
            return ;
        }
        //验证成功
        User user1 =userMapper.selectByAccount(auth.getAccount());
        if(user1!=null)
        {
            log.info("用户已存在");
            //抛出已存在异常
            return ;
        }
        User user =new User();
        user.setAccount(auth.getAccount());
        user.setPassword(auth.getPassword());
        user.setNickname(auth.getNickname());
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
    @Transactional
    public LoginInfo userLogin(User user) {
        log.info("用户登录:{}",user);
        User user1=userMapper.selectByAccountAndPasswordAndRole0(user);
        authMapper.updateStatus(user1);
        if (user1==null)
        {
            //抛出用户不存在异常
            return null;
        }
        Map<String ,Object> claims= new HashMap<>();
        claims.put("id",user1.getUserID());
        claims.put("role",user1.getRole());
        String token = JwtUtils.generateJwt(claims);
        log.info("用户登录成功:{}",user1);
        LoginInfo loginInfo = new LoginInfo(user1.getUserID(),user1.getAccount(),user1.getNickname(),user1.getRole(),token);
        log.info("用户登录信息:{}",loginInfo);
        return loginInfo;
    }

    @Override
    @Transactional
    public LoginInfo adminLogin(User user) {
        log.info("管理员登录:{}",user);

        User user1=userMapper.selectByAccountAndPasswordAndRole1(user);
        authMapper.updateStatus(user1);
        if (user1==null)
        {
            //抛出用户不存在异常
            return null;
        }
        Map<String ,Object> claims= new HashMap<>();
        claims.put("id",user1.getUserID());
        claims.put("role",user1.getRole());
        String token = JwtUtils.generateJwt(claims);
        log.info("管理员登录成功:{}",user1);
        LoginInfo loginInfo = new LoginInfo(user1.getUserID(),user1.getAccount(),user1.getNickname(),user1.getRole(),token);
        log.info("管理员登录信息:{}",loginInfo);
        return loginInfo;
    }

    @Transactional
    @Override
    public void logout(Integer id) {
        log.info("用户登出:id={}",id);
        //authMapper.deleteVerificationCode(user.getAccount());
        authMapper.updateLastLoginTimeAndStatus(LocalDateTime.now(),id);
        authMapper.logout(LocalDateTime.now(),id);
    }


}
