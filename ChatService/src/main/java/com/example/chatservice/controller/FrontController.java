package com.example.chatservice.controller;

import com.example.chatservice.service.FrontService;
import com.example.global.Code;
import com.example.global.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/chat")
@CrossOrigin
public class FrontController {

    @Resource
    private FrontService frontServiceImpl;

    @PostMapping("/simpleChat")
    public Result getGPTAnswer(String start_place, String destination, String start_time){
        String res = frontServiceImpl.getGPTAnswer(start_place);
        return Result.success().code(Code.CODE_200).data(res);
    }

    @GetMapping("/flask")
    public Result testFlask(){
        String str = frontServiceImpl.testFlask();
        return Result.success().message("springboot和flask通讯成功!").code(Code.CODE_200).data(str);
    }
}
