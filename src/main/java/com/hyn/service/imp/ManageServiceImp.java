package com.hyn.service.imp;

import com.hyn.mapper.ManageMapper;
import com.hyn.pojo.Manage;
import com.hyn.pojo.Result;
import com.hyn.pojo.User;
import com.hyn.service.ManageService;
import com.hyn.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManageServiceImp implements ManageService {
    @Autowired
    ManageMapper manageMapper;
//    登录功能，根据账号密码查询用户
    @Override
    public Result getManage(Manage manage) {
        Manage ma = manageMapper.getManage(manage);
        if(ma==null){
            return Result.error("账号不存在");
        }
        else{
            Map<String,Object> m = new HashMap<>();
            m.put("id",manage.getId());
            m.put("username",manage.getName());
            m.put("password",manage.getPassword());
            String jwt = JwtUtils.createJwt(m);
            return Result.success(jwt);
        }
    }

    @Override
    public Result getAdminInfo(String adminToken) {
        Map<String, Object> s = JwtUtils.parseJwt(adminToken);
        Object username = s.get("username");
        return Result.success(username);
    }

    @Override
    public void getUserInfo() {
        manageMapper.getUserInfo();
    }

    @Override
    public Result getUserCount(String name, String role) {
        int userCount = manageMapper.getUserCount(name, role);
        if(userCount==0){
            return Result.error("没有找到该用户");
        }
        return Result.success(userCount);
    }

    @Override
    public Result getUsers(String name, String role,int currentPage,int pageSize) {
        List<User> users = manageMapper.getUsers(name, role,currentPage,pageSize);
        return Result.success(users);
    }


}
