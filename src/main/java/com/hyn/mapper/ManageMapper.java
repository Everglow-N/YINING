package com.hyn.mapper;

import com.hyn.pojo.Manage;
import com.hyn.pojo.Result;
import com.hyn.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface ManageMapper {
    @Select("select * from management where name = #{name} and password=#{password}")
    Manage getManage(Manage manage);
    @Select("select * from user limit")
    void getUserInfo();

    int getUserCount(String name, String role);

    List<User> getUsers(String name, String role,int currentPage, int pageSize);
}
