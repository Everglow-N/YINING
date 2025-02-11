package com.hyn.service;

import com.hyn.pojo.Result;
import com.hyn.pojo.User;

import java.time.LocalDate;

public interface UserService {
    Boolean selectUserByName(String username);

    Result selectUser(String username, String password);

    User getUserInfo(String token);

    void register(String username, String password, String email, LocalDate registerTime);

    Result modifyInfo(User user,String token);
}
