package com.hyn.controller;

import com.hyn.pojo.Manage;
import com.hyn.pojo.Result;
import com.hyn.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8081/")
public class ManageController {
    @Autowired
    private ManageService manageService;

    @PostMapping("/admin/login")
    public Result ManageLogin(@RequestBody Manage manage) {
        return manageService.getManage(manage);
    }

    @GetMapping("/admin/getAdminInfo")
    public Result getAdminInfo(@RequestHeader String adminToken) {
        return manageService.getAdminInfo(adminToken);
    }

    @GetMapping("/admin/getUserCount")
    public Result getUserCount(@RequestParam String name, @RequestParam String role) {
        return manageService.getUserCount(name, role);
    }

    @GetMapping("/admin/getUsers")
    public Result getUsers(@RequestParam String name, @RequestParam String role,@RequestParam int currentPage, @RequestParam int pageSize) {
        return manageService.getUsers(name,role,currentPage,pageSize);
    }

}
