package com.example.demo.service.Impl;

import com.example.demo.mapper.EventImageMapper;
import com.example.demo.mapper.HappyEventMapper;
import com.example.demo.mapper.PublicMapper;
import com.example.demo.mapper.RecordMapper;
import com.example.demo.pojo.*;
import com.example.demo.service.HappyEventService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class HappyEventServiceImpl implements HappyEventService {
    @Autowired
    private HappyEventMapper happyEventMapper;
    @Autowired
    private EventImageMapper eventImageMapper;
    @Autowired
    private RecordMapper recordMapper;
    @Autowired
    private PublicMapper publicMapper;

    //个人的 活动

    /**
     *  个人发布事件
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void addEvent(HappyEvent happyEvent) {
        happyEvent.setCreateTime(LocalDateTime.now());
        happyEvent.setUpdateTime(LocalDateTime.now());
        log.info("添加活动:{}",happyEvent);
        List<EventImage> images=happyEvent.getImages();
        happyEventMapper.insert(happyEvent);
        //将图片数据保存到eventimage表中
        if(!CollectionUtils.isEmpty(images))
        {
            images.forEach(image->{
                log.info("这是主键返回的id"+happyEvent.getEventID());
                image.setUploadTime(LocalDateTime.now());
                image.setEventId(happyEvent.getEventID());
            });
            eventImageMapper.insertBatch(images);
        }



    }
    /**
     *  获取个人活动
     */
    @Transactional
    @Override
    public PageResult<HappyEvent> getMyEvent(Integer id, EventQueryParam param) {
        //设置分页参数
        PageHelper.startPage(param.getPage(),param.getSize());
        //执行查询
        log.info("\n获取当前用户活动:id={},param={}\n",id,param);
        List<HappyEvent> happyEventList= happyEventMapper.selectById(id);
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
        log.info("\n获取当前用户活动:{}\n",p);
        return new PageResult<HappyEvent>(p.getTotal(),p.getResult());
    }
    /**
     *  获取活动详情
     */
    @Override
    public HappyEvent getEventDetail(Integer UserId,Integer EventId) {
        log.info("\n获取活动详情:{}\n",EventId);
        HappyEvent happyEvent= happyEventMapper.selectByEventId(EventId);
        Map<Integer,Long> EventLikeMap=new HashMap<>();
        Map<Integer,Long> EventCommentMap=new HashMap<>();
        Integer eventId=happyEvent.getEventID();
        List<Integer> eventIDs= Collections.singletonList(eventId);
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
        happyEvent.setLikeCount(EventLikeMap.getOrDefault(eventId,0L));
        happyEvent.setCommentCount(EventCommentMap.getOrDefault(eventId,0L));
        //获取是否点赞
        Boolean isLike=publicMapper.selectLikeById(UserId,eventId);
        happyEvent.setIsLike(isLike!=null);
        log.info("\n获取活动详情:{}\n",happyEvent);
        return happyEvent;
    }
    /**
     *  修改活动
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void updateEvent(Integer id,HappyEvent event) {
        event.setUserID(id);
        event.setUpdateTime(LocalDateTime.now());
        List<EventImage> images = event.getImages();
        images.forEach(image->{
            image.setEventId(event.getEventID());
            image.setUploadTime(LocalDateTime.now());
        });
        eventImageMapper.deleteByEventId(event.getEventID());
        happyEventMapper.updateEvent(event);
        eventImageMapper.insertBatch(images);
        log.info("\n用户id{}修改活动成功\n",id);
    }
    /**
     *  删除活动
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void deleteEvent(Integer eventId) {
        log.info("\n删除活动及其照片:{}",eventId);
        eventImageMapper.deleteByEventId(eventId);
        happyEventMapper.deleteByEventId(eventId);
        publicMapper.deleteLikeByEventId(eventId);
        publicMapper.deleteCommentByEventId(eventId);
    }



}
