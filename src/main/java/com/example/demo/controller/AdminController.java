package com.example.demo.controller;

import com.example.demo.pojo.*;
import com.example.demo.service.AdminService;
import com.example.demo.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;


    /**
     * 获取所有用户
     */
    @GetMapping("/users")
    public Result getAllUser(HttpServletRequest request,@RequestBody UserQueryParam param){
        Integer id= JwtUtils.getId(request);
        log.info("getAllEvent");
        PageResult<User> userList = adminService.getAllUser(id,param);
        return Result.success(userList);
    }

    /**
     * 修改用户密码
     */
    @PutMapping("/users/{userId}/{password}")
    public Result ChangeUserPassword(HttpServletRequest request,@PathVariable Integer userId,@PathVariable String password)
    {
        Integer id =JwtUtils.getId(request);
        adminService.changeUserPassword(userId,password);
        return Result.success();
    }

    /**
     * 获取所有活动
     */
    @GetMapping("/events")
    //加上筛选功能,注意要使用动态sql
    public Result getAllEvent(HttpServletRequest request,@RequestBody EventQueryParam param){
        Integer id= JwtUtils.getId(request);
        log.info("getAllEvent");
        PageResult<HappyEvent> events = adminService.getAllEvents(id,param);
        return Result.success(events);
    }

    /**
     * 修改活动状态
     */
    @PutMapping("/events/{eventId}/{status}")
    public Result changeEventStatus(HttpServletRequest request,@PathVariable Integer eventId,@PathVariable Integer status)
    {
        Integer id =JwtUtils.getId(request);
        adminService.changeEventStatus(eventId,status);
        return Result.success();
    }

    /**
     * 删除活动
     */
    @DeleteMapping("/events/{eventId}")
    public Result deleteEvent(@PathVariable Integer eventId){
        adminService.deleteEvent(eventId);
        return Result.success();
    }

    /**
     * 修改图片状态
     */
    @PutMapping("/images/{imageId}/{status}")
    public Result changeImageStatus(HttpServletRequest request,@PathVariable Integer imageId,@PathVariable String status)
    {
        Integer id =JwtUtils.getId(request);
        adminService.changeImageStatus(imageId,status);
        return Result.success();
    }
    /**
     * 批量修改用户图片状态
     */
    //注意后端将图片资源发给前端时，需要处理，违规图片需要替换为默认违规提示图片
    @PutMapping("/users/{userId}/images/{status}")
    public Result changeUserImageStatus(HttpServletRequest request,@PathVariable Integer userId,@PathVariable Integer status)
    {
        Integer id =JwtUtils.getId(request);
        adminService.changeUserImageStatus(userId,status);
        return Result.success();
    }














}
