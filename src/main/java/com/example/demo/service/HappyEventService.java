package com.example.demo.service;

import com.example.demo.pojo.Comment;
import com.example.demo.pojo.EventQueryParam;
import com.example.demo.pojo.HappyEvent;
import com.example.demo.pojo.PageResult;

import java.util.List;

public interface HappyEventService {
    void addEvent(HappyEvent happyEvent);

    PageResult<HappyEvent> getMyEvent(Integer id, EventQueryParam param);

    HappyEvent getEventDetail(Integer UserId,Integer EventId);

    void updateEvent(Integer id, HappyEvent happyEvent);

    void deleteEvent(Integer eventId);


}
