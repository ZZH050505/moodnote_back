package com.example.demo.service.Impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getProfile(Integer id) {
        User user= userMapper.selectById(id);
        log.info("用户信息:{}",user);
        return user;
    }

    @Override
    public void updateProfile(Integer id,String nickname) {
        userMapper.updateProfile(id,nickname);
        log.info("用户更新成功:id={},nickname={}",id,nickname);
        return;
    }
}
