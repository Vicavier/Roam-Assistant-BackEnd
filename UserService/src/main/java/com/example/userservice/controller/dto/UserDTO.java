package com.example.userservice.controller.dto;

import lombok.Data;

@Data
public class UserDTO {
    private int user_id;
    private String user_name;
    private String user_passwd;
    private String token;
}
