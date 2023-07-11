package com.example.global;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 所有后端向前端返回的结果，都应该为Result对象，Result对象请使用链式编程创建，不要暴力破解
 */
@Data
@NoArgsConstructor
public class Result {

    private int code;
    private String message;
    private Object data;

    public static Result success(){
        Result result=new Result();
        return result;
    }

    public static Result error(){
        Result result=new Result();
        return result;
    }

    public Result message(String message){
        this.message=message;
        return this;
    }

    public Result code(int code){
        this.code=code;
        return this;
    }

    public Result data(Object data){
        this.data=data;
        return this;
    }
}