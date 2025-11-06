package com.example.demo.service.Impl;

import com.example.demo.mapper.*;
import com.example.demo.pojo.*;
import com.example.demo.service.AdminService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private HappyEventMapper happyEventMapper;
    @Autowired
    private PublicMapper publicMapper;
    @Autowired
    private EventImageMapper eventImageMapper;


    @Override
    public PageResult<User> getAllUser(Integer id, UserQueryParam param) {
        //设置分页参数
        PageHelper.startPage(param.getPage(),param.getSize());
        //进行查询
        List<User> userList=adminMapper.selectAllUserByParam(param);
        //进行封装
        Page<User> p=(Page<User>)userList;
        return new PageResult<User>(p.getTotal(),p.getResult());
    }

    @Override
    public void changeUserPassword(Integer userId, String password) {
        adminMapper.updateUserPassword(userId,password);
    }

    @Override
    public void changeEventStatus(Integer eventId, Integer status) {
        adminMapper.updateEventStatus(eventId,status);
    }

    @Override
    public void deleteEvent(Integer eventId) {
        log.info("\n删除活动及其照片:{}",eventId);
        eventImageMapper.deleteByEventId(eventId);
        happyEventMapper.deleteByEventId(eventId);
        publicMapper.deleteLikeByEventId(eventId);
        publicMapper.deleteCommentByEventId(eventId);
    }

    @Override
    public void changeImageStatus(Integer imageId, String status) {
        adminMapper.updateImageStatus(imageId,status);
    }

    @Override
    public void changeUserImageStatus(Integer userId, Integer status) {
        adminMapper.updateUserImageStatus(userId,status);
    }

    /**
     * 获得所有事件，不管其状态或者隐藏与否
     * 图片也都原样输出
     */
    @Override
    public PageResult<HappyEvent> getAllEvents(Integer id, EventQueryParam param) {
        //设置分页参数
        PageHelper.startPage(param.getPage(),param.getSize());
        //执行查询操作
        List<HappyEvent> events=adminMapper.selectEventByParam(param);
        //进行封装
        Page<HappyEvent> p=(Page<HappyEvent>)events;

        return new PageResult<>(p.getTotal(),p.getResult());
    }


}
