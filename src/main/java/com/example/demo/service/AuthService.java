package com.example.demo.service;

import com.example.demo.pojo.LoginInfo;
import com.example.demo.pojo.User;
import com.example.demo.pojo.Auth;

public interface AuthService {
    void register(Auth  auth);

    void sendCode(User user);

    LoginInfo userLogin(User user);

    LoginInfo adminLogin(User user);

    void logout(Integer id);
}
