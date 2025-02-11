package com.hyn.mapper;

import com.hyn.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;

@Mapper
public interface UserMapper {
    @Select("select * from user where (email = #{username} or username = #{username}) and password = #{password}")
    User selectUser(String username, String password);

    @Select("select * from user where id = #{id}")
    User getUserInfo(int id);

    @Insert("insert into user(username,password,email,register_time) values (#{username},#{password},#{email},#{registerTime})")
    void register(String username, String password, String email, LocalDate registerTime);


    void modifyInfo(User user);

//    根据用户名查询用户
    @Select("select * from user where username = #{username}")
    User selectUserByName(String username);
}
