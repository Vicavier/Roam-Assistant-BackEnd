package com.example.userservice.Utils;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.userservice.controller.dto.UserDTO;

import java.util.Date;

public class TokenUtil {
    public static String generateToken(UserDTO user){
        return JWT.create().withAudience(String.valueOf(user.getUser_id())).
                withExpiresAt(DateUtil.offsetHour(new Date(),2)).   //2h后token过期
                sign(Algorithm.HMAC256(user.getUser_passwd()));
    }
}
