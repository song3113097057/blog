package com.sxy.service;
import com.sxy.entity.User;


public interface UserService{

    User checkUser(String username,String password);

}
