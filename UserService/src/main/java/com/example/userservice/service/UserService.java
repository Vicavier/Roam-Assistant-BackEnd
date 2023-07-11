package com.example.userservice.service;


import com.example.userservice.controller.dto.UserDTO;
import com.example.userservice.entity.User;

public interface UserService {
    public int save(User user);

    UserDTO login(UserDTO userDTO);
}
