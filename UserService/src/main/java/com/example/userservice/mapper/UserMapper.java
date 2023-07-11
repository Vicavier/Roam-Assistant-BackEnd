package com.example.userservice.mapper;

import com.example.userservice.controller.dto.UserDTO;
import com.example.userservice.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT user_id,user_name,user_passwd FROM t_user")
    List<User> getAllUser();

    @Insert("INSERT INTO t_user(user_name, user_passwd) VALUES (#{user_name},#{user_passwd})")
    Integer insert(User user);

    @Update("UPDATE t_user SET user_passwd=#{user_passwd} WHERE user_id=#{user_id}")
    Integer update(User user);

    @Select("SELECT user_id,user_name,user_passwd FROM t_user WHERE user_name=#{user_name}")
    UserDTO login(UserDTO userDTO);
}
