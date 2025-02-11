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

    @PostMapping("/manage/login")
    public Result ManageLogin(@RequestBody Manage manage) {
       return manageService.getManage(manage);
    }
}
