package com.example.demo.controller;

import com.example.demo.pojo.Auth;
import com.example.demo.pojo.LoginInfo;
import com.example.demo.pojo.Result;
import com.example.demo.pojo.User;
import com.example.demo.service.AuthService;
import com.example.demo.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    //获取验证码功能还没写
    //返回一个jwt令牌
    @PostMapping("/getCode")
    public Result getCode(@RequestBody User user){
        log.info("getCode");
        authService.sendCode(user);
        return Result.success();
    }

    @PostMapping("/register")
    public Result register(@RequestBody Auth auth){
        log.info("register");
        authService.register(auth);
        return Result.success();
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        log.info("login:{}",user);
        LoginInfo loginInfo=authService.userLogin(user);
        return Result.success(loginInfo);
    }

    @PostMapping("/admin/login")
    public Result adminLogin(@RequestBody User user){
        log.info("adminLogin:{}",user);
        LoginInfo loginInfo=authService.adminLogin(user);
        return Result.success(loginInfo);
    }

    @PostMapping("/logout")
    public Result logout(HttpServletRequest request){
        String token = request.getHeader("token");
//        String userAgent = request.getHeader("User-Agent");
//        String contentType = request.getHeader("Content-Type");
        Claims claims = JwtUtils.parseJwt(token);
        log.info("logout:token=={}",token);
//        log.info("logout:userAgent=={}",userAgent);
//        log.info("logout:contentType=={}",contentType);
        Integer id=(Integer) claims.get("id");

        log.info("logout:id=={}",id);
        authService.logout(id);
        return Result.success();
    }

}
