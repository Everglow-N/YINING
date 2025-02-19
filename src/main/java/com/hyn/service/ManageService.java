package com.hyn.service;

import com.hyn.pojo.Manage;
import com.hyn.pojo.Result;

public interface ManageService {
    Result getManage(Manage manage);

    Result getAdminInfo(String adminToken);

    void getUserInfo();

    Result getUserCount(String name, String role);

    Result getUsers(String name, String role,int currentPage, int pageSize);
}
