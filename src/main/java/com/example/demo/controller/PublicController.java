package com.example.demo.controller;

import com.example.demo.pojo.Comment;
import com.example.demo.pojo.EventQueryParam;
import com.example.demo.pojo.PageResult;
import com.example.demo.pojo.Result;
import com.example.demo.service.PublicService;
import com.example.demo.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@Slf4j
public class PublicController {
    @Autowired
    private PublicService publicService;


    @GetMapping("/public")
    public Result getPublicEvent(@RequestBody EventQueryParam param)
    {
        log.info("查询公共事件:{}",param);
        PageResult pageResult=publicService.getPublicEvent(param);
        return Result.success(pageResult);
    }

    @PostMapping("/{eventId}/like")
    public Result LikeEvent(HttpServletRequest request, @PathVariable Integer eventId)
    {
        Integer id= JwtUtils.getId(request);
        log.info("user{}对事件event{}点赞",id,eventId);
        publicService.LikeEvent(id,eventId);
        return Result.success();
    }

    @DeleteMapping("/{eventId}/like")
    public Result DeleteLikeEvent(HttpServletRequest request,@PathVariable Integer eventId)
    {
        Integer id=JwtUtils.getId(request);
        log.info("user{}对事件event{}取消点赞",id,eventId);
        publicService.DeleteLikeEvent(id,eventId);
        return Result.success();
    }

    @PostMapping("/{eventId}/comments")
    public Result addComment(HttpServletRequest request,@PathVariable Integer eventId,@RequestBody Comment content)
    {
        Integer id=JwtUtils.getId(request);
        log.info("user{}对事件event{}评论:{}",id,eventId,content.getContent());
        publicService.addComment(id,eventId,content.getContent());
        return Result.success();
    }

    @GetMapping("/{eventId}/comments")
    public Result getComments(@PathVariable Integer eventId)
    {
        log.info("获取事件event{}的评论",eventId);
        List<Comment> comment=publicService.getComments(eventId);
        return Result.success(comment);
    }

    @DeleteMapping("/comments/{commentId}")
    public Result deleteComment(HttpServletRequest request,@PathVariable Integer commentId)
    {
        Integer id=JwtUtils.getId(request);
        log.info("删除评论:{}",commentId);
        publicService.deleteComment(id,commentId);
        return Result.success();
    }


}
