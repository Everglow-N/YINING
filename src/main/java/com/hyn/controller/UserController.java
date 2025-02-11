package com.hyn.controller;

import com.hyn.pojo.Result;
import com.hyn.pojo.User;
import com.hyn.pojo.VerificationCode;
import com.hyn.service.EmailService;
import com.hyn.service.UserService;
import com.hyn.utils.JwtUtils;
import com.hyn.utils.VerificationCodeUtil;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

import static java.awt.SystemColor.info;

@Slf4j
@CrossOrigin(origins = "http://localhost:8081/")
@RestController
public class UserController {
//    这个映射关系专门用于验证码过期时间
    HashMap<String, VerificationCode> verificationCodes = new HashMap<>();

    @Autowired
    private UserService userService;
    @Autowired
    EmailService emailService;

    @PostMapping("/login")
    public Result userLogin(@RequestBody User user) {
        log.info(user.getUsername()+"登录");
        return userService.selectUser(user.getUsername(),user.getPassword());
    }
//  5分钟后自动清除过期的验证码
    @Scheduled(fixedRate = 600000)  // 60秒
    public void cleanupExpiredCodes() {
        LocalDateTime now = LocalDateTime.now();
        verificationCodes.entrySet().removeIf(entry -> entry.getValue().isExpired());
    }

    @GetMapping("/user/getUserInfo")
    public Result getUserInfo(@RequestHeader String token) {
        if (token == null || token.equals("")) {
            return Result.error("token验证失败");
        }
        User userInfo = userService.getUserInfo(token);
        if (userInfo != null) {
            return Result.success(userInfo);
        } else {
            return Result.error("token验证失败");
        }
    }

    @GetMapping("/getEmailCode")
    public Result getEmailCode(@RequestParam String email) {
        try {
            //生成一个随机6位数的验证码
            String s = VerificationCodeUtil.generateVerificationCode();
//        将时间时间设为5分钟后
            LocalDateTime expireTime = LocalDateTime.now().plusMinutes(5);
//        将发送来的email和 携带验证码和时间的对象存储到verificationCodes映射中
            verificationCodes.put(email,new VerificationCode(s,expireTime));
//        发送验证码
            emailService.sendVerificationCode(email,s);
            return Result.success("验证码已经发送成功！");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Result.error("验证码发送失败，请联系管理员");
        }
    }

    @PostMapping("/register")
    public Result register(String username, String password, String email, String code) {
        VerificationCode storedCode = verificationCodes.get(email);  // 获取存储的验证码
        if (storedCode == null) {
            return Result.error("验证码错误");
        }

        // 检查验证码是否过期
        if (storedCode.isExpired()) {
            verificationCodes.remove(email);  // 移除过期的验证码
            return Result.error("验证码已经过期了！");
        }

        if (!userService.selectUserByName(username)) {
            return Result.error("用户名已经存在");
        }
        // 检查验证码是否正确
        if (storedCode.getCode().equals(code)) {
            try {
                verificationCodes.remove(email);  // 验证成功后移除验证码
                LocalDate registerTime = LocalDate.now();
                userService.register(username,password,email,registerTime);
                log.info(username+"已经注册成功！");
                return Result.success("注册成功！");
            } catch (Exception e) {
                return Result.error("注册失败，请联系管理员");
            }
        } else {
            return Result.error("验证码验证失败！请重新输入");
        }
    }
//   验证邮箱验证码
    @GetMapping("/verifyCode")
    public Result verifyCode(@RequestParam String email,@RequestParam String code) {
        VerificationCode storedCode = verificationCodes.get(email);
        if (storedCode == null) {
            return Result.error("验证码错误");
        }
        // 检查验证码是否过期
        if (storedCode.isExpired()) {
            verificationCodes.remove(email);  // 移除过期的验证码
            return Result.error("验证码已经过期了！");
        }
        if (storedCode.getCode().equals(code)) {
            try {
                verificationCodes.remove(email);  // 验证成功后移除验证码
                log.info("个人中心修改验证码验证成功");
                return Result.success("个人中心邮箱修改验证完成！");
            } catch (Exception e) {
                return Result.error("验证失败，请联系管理员");
            }
        } else {
            return Result.error("验证码验证失败！请重新输入");
        }
    }

    @PostMapping("/user/modifyInfo")
    public Result modifyInfo(@RequestBody User user ,@RequestHeader String token) {
        System.out.println(user);
        System.out.println(token);
        return userService.modifyInfo(user,token);
    }
}
