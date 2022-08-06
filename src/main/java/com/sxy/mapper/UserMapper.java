package com.sxy.mapper;

import com.sxy.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper{
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}