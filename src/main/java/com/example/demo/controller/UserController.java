package com.example.demo.controller;


import com.example.demo.pojo.Result;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public Result getProfile(HttpServletRequest  request) {
        String token = request.getHeader("token");
        Claims claims = JwtUtils.parseJwt(token);
        Integer id = (Integer) claims.get("id");
        User user = userService.getProfile(id);
        return Result.success(user);
    }

    @PostMapping("/profile")
    public Result updateProfile(HttpServletRequest  request,@RequestParam String nickname) {
        String token = request.getHeader("token");
        Claims claims = JwtUtils.parseJwt(token);
        Integer id = (Integer) claims.get("id");
        userService.updateProfile(id,nickname);
        return Result.success();
    }



}
