package com.hyn.service.imp;

import com.hyn.mapper.UserMapper;
import com.hyn.pojo.Result;
import com.hyn.pojo.User;
import com.hyn.service.UserService;
import com.hyn.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserMapper userMapper;
// 根据用户名查询用户信息
    @Override
    public Boolean selectUserByName(String username) {
        User user = userMapper.selectUserByName(username);
        if(user==null){
            return true;
        }
        else {
            return false;
        }
    }

    //  登录，如果根据用户名密码查到，则生成jwt令牌
    @Override
    public Result selectUser(String username, String password) {
       User u = userMapper.selectUser(username,password);
        if(u != null){
            Map<String,Object> m = new HashMap<>();
            m.put("id",u.getId());
            m.put("username",u.getUsername());
            m.put("password",u.getPassword());
            String jwt = JwtUtils.createJwt(m);
            return Result.success(jwt);
        }
        return Result.error("对不起，登录失败了！");
    }
//获取用户的信息
    @Override
    public User getUserInfo(String token) {
        try {
            Map<String, Object> users = JwtUtils.parseJwt(token);
            int id = (int) users.get("id");
            User userInfo = userMapper.getUserInfo(id);
            return userInfo;
        } catch (Exception e) {
            System.out.println("失败了么");
            return null;
        }
    }

    @Override
    public void register(String username, String password, String email, LocalDate registerTime) {
        userMapper.register(username,password,email,registerTime);
    }

    @Override
    public Result modifyInfo(User user,String token) {
        Map<String, Object> jwt = JwtUtils.parseJwt(token);
        int id = (int) jwt.get("id");
        user.setId(id);

        try {
            userMapper.modifyInfo(user);
            return Result.success("修改成功！");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Result.error("修改失败");
        }
    }

}