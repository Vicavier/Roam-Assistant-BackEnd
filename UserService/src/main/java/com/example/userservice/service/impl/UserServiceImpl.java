package com.example.userservice.service.impl;


import com.example.userservice.controller.dto.UserDTO;
import com.example.userservice.entity.User;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    public int save(User user){
        if (user.getUser_id() == null){
            return userMapper.insert(user);
        } else {
            return userMapper.update(user);
        }
    }

    @Override
    public UserDTO login(UserDTO userDTO) {
        return userMapper.login(userDTO);
    }
}
