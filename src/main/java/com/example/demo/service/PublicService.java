package com.example.demo.service;

import com.example.demo.pojo.Comment;
import com.example.demo.pojo.EventQueryParam;
import com.example.demo.pojo.PageResult;

import java.util.List;

public interface PublicService {
    PageResult getPublicEvent(EventQueryParam param);

    void LikeEvent(Integer id, Integer eventId);

    void DeleteLikeEvent(Integer id, Integer eventId);

    void addComment(Integer id, Integer eventId, String content);

    void deleteComment(Integer UserId,Integer CommentId);

    List<Comment> getComments(Integer eventId);
}
