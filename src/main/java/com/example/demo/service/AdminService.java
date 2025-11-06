package com.example.demo.service;

import com.example.demo.pojo.*;
import com.github.pagehelper.Page;

import java.util.List;

public interface AdminService {
    PageResult<User> getAllUser(Integer id, UserQueryParam param);

    void changeUserPassword(Integer userId, String password);

    void changeEventStatus(Integer eventId, Integer status);

    void deleteEvent(Integer eventId);

    void changeImageStatus(Integer imageId, String status);

    void changeUserImageStatus(Integer userId, Integer status);

    PageResult<HappyEvent> getAllEvents(Integer id, EventQueryParam param);
}
