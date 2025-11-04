package com.example.demo.service;

import com.example.demo.pojo.User;

public interface UserService {
    User getProfile(Integer id);

    void updateProfile(Integer id,String nickname);
}
