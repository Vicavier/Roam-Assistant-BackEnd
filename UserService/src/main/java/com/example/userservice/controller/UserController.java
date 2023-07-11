package com.example.userservice.controller;

import com.example.global.Code;
import com.example.global.Result;
import cn.hutool.core.util.StrUtil;
import com.example.userservice.Utils.TokenUtil;
import com.example.userservice.controller.dto.UserDTO;
import com.example.userservice.entity.User;
import com.example.userservice.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource()
    private UserService userServiceImpl;

    @PostMapping("/register")
    public Integer save(@RequestBody User user){
        return userServiceImpl.save(user);
    }

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO){
        String userName = userDTO.getUser_name();
        String userPasswd = userDTO.getUser_passwd();
        if (StrUtil.isBlank(userName) || StrUtil.isBlank(userPasswd)){
            return Result.error().message("请填写完整用户名和密码！");
        } else {
            UserDTO res =  userServiceImpl.login(userDTO);
            if (res == null || !res.getUser_passwd().equals(userDTO.getUser_passwd()))
                return Result.error().message("用户名或密码不正确！");
            else {
                res.setToken(TokenUtil.generateToken(res));
                return Result.success().code(Code.CODE_200).message("登录成功！").data(res);
            }
        }
    }
}
