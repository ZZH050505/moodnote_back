package com.example.demo.controller;


import com.example.demo.pojo.*;
import com.example.demo.service.HappyEventService;
import com.example.demo.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/events")
public class HappyEventController {
    @Autowired
    private HappyEventService happyEventService;
    @Autowired
    private Environment environment;


    @PostMapping
    public Result addEvent(HttpServletRequest request,@RequestBody HappyEvent happyEvent) {
        Integer id=JwtUtils.getId(request);
        happyEvent.setUserID(id);
        log.info("\n添加活动:{}\n",happyEvent);
        happyEventService.addEvent(happyEvent);
        return Result.success();
    }

    @GetMapping("/my")
    public Result getMyEvent(HttpServletRequest request, @RequestBody EventQueryParam param) {
        Integer id=JwtUtils.getId(request);
        log.info("\n获取当前用户活动:{},{}\n",id,param);
        PageResult pageResult=happyEventService.getMyEvent(id,param);
        log.info("\n获取当前用户活动:{}\n",pageResult);
        return Result.success(pageResult);
    }

    @GetMapping("/{eventId}")
    public Result getEventDetail(HttpServletRequest  request,@PathVariable Integer eventId) {
        Integer id=JwtUtils.getId(request);
        log.info("\n获取活动详情:eventId:{}\n",eventId);
        HappyEvent happyEvent=happyEventService.getEventDetail(id,eventId);
        return Result.success(happyEvent);
    }

    @PutMapping
    public Result updateEvent(HttpServletRequest request,@RequestBody HappyEvent happyEvent) {
        Integer id=JwtUtils.getId(request);

        log.info("\n用户id{}修改活动{}\n",id,happyEvent);
        happyEventService.updateEvent(id,happyEvent);
        return Result.success();
    }

    @DeleteMapping("/{eventId}")
    public Result deleteEvent(HttpServletRequest request,@PathVariable Integer eventId) {
        log.info("\n删除活动:{}\n",eventId);
        happyEventService.deleteEvent(eventId);
        return Result.success();
    }




}
