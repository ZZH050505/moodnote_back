package com.example.demo.controller;

import com.example.demo.pojo.Auth;
import com.example.demo.pojo.Result;
import com.example.demo.pojo.User;
import com.example.demo.service.AuthService;
import com.example.demo.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
public class authController {
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
    public Result register(@RequestBody User user){
        log.info("register");
        authService.register(user);
        return Result.success();
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        log.info("login:{}",user);
        authService.userLogin(user);
        return Result.success();
    }

    @PostMapping("/admin/login")
    public Result adminLogin(@RequestBody User user){
        log.info("adminLogin:{}",user);
        authService.adminLogin(user);
        return Result.success();
    }

    @PostMapping("/logout")
    public Result logout(@RequestBody User user){
        log.info("logout:{}",user);
        authService.logout(user);
        return Result.success();
    }

}
