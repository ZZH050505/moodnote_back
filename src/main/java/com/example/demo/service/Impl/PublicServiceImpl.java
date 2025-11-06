package com.example.demo.service.Impl;

import com.example.demo.mapper.HappyEventMapper;
import com.example.demo.mapper.PublicMapper;
import com.example.demo.mapper.RecordMapper;
import com.example.demo.pojo.*;
import com.example.demo.service.PublicService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PublicServiceImpl implements PublicService {
    @Autowired
    private RecordMapper recordMapper;
    @Autowired
    private PublicMapper publicMapper;
//    @Autowired
//    private HappyEventMapper happyEventMapper;

    @Override
    public PageResult getPublicEvent(EventQueryParam param) {
        //需要是公开的才能查到
        log.info("查询公共事件,{}",param);
        //设置分页参数
        PageHelper.startPage(param.getPage(),param.getSize());
        //执行查询
        List<HappyEvent> happyEventList=publicMapper.selectAllPublic();
        //获取点赞和评论数
        //先获得该用户的所有事件数组
        List<Integer> eventIDs=happyEventList.stream().map(HappyEvent::getEventID).toList();
        //获取点赞和评论数
        Map<Integer,Long> EventLikeMap=new HashMap<>();
        Map<Integer,Long> EventCommentMap=new HashMap<>();
        List<Map<String, Object>> eventLike= recordMapper.getEventLikeMap(eventIDs);
        List<Map<String, Object>> eventComment= recordMapper.getEventCommentMap(eventIDs);
        //单个的格式: {"EventID":1, "LikeCount":1}
        for(Map<String, Object> map:eventLike)
        {
            EventLikeMap.put((Integer) map.get("EventID"),(Long) map.get("time"));
        }
        for(Map<String, Object> map:eventComment)
        {
            EventCommentMap.put((Integer) map.get("EventID"),(Long) map.get("time"));
        }
        for(HappyEvent event:happyEventList)
        {
            event.setLikeCount(EventLikeMap.getOrDefault(event.getEventID(),0L));
            event.setCommentCount(EventCommentMap.getOrDefault(event.getEventID(),0L));
        }
        //解析查询结果，进行封装
        Page<HappyEvent> p=(Page<HappyEvent>) happyEventList;

        log.info("\n获取所有活动:{}\n",p);
        return new PageResult<HappyEvent>(p.getTotal(),p.getResult());
    }

    @Override
    public void LikeEvent(Integer id, Integer eventId) {
        LikeRecord likeRecord=new LikeRecord();
        likeRecord.setEventID(eventId);
        likeRecord.setUserID(id);
        likeRecord.setLikeTime(LocalDateTime.now());
        publicMapper.insertLikeRecord(likeRecord);
    }

    @Override
    public void DeleteLikeEvent(Integer id, Integer eventId) {
        publicMapper.deleteLikeRecord(id,eventId);
    }

    @Override
    public void addComment(Integer id, Integer eventId, String content) {
        Comment comment=new Comment();
        comment.setEventID(eventId);
        comment.setUserID(id);
        comment.setContent(content);
        comment.setCommentTime(LocalDateTime.now());
        publicMapper.insert(comment);
    }

    @Override
    public void deleteComment(Integer UserId,Integer CommentId) {
        //删除评论
        publicMapper.deleteComment(UserId,CommentId);
    }

    @Override
    public List<Comment> getComments(Integer eventId) {
        //获取评论，之后再通过评论的userId查询用户信息，获取名字
        //按时间排序
        List<Comment> commentList=publicMapper.getEventCommentByEventId(eventId);
        log.info("获取事件{}的评论:{}",eventId,commentList);
        return commentList;
    }





}
