package com.sxy.service.impl;

import com.sxy.entity.User;
import com.sxy.mapper.UserMapper;
import com.sxy.service.UserService;
import com.sxy.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User checkUser(String username, String pasword) {
        User user =userMapper.findByUsernameAndPassword(username, MD5Utils.code(pasword));
        return user;
    }
}

