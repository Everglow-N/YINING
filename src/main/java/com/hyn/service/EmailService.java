package com.hyn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;  // Spring Boot自动配置的JavaMailSender

    /**
     * 发送验证码邮件
     *
     * @param toEmail         收件人邮箱
     * @param verificationCode 验证码
     */
    @Value("${spring.mail.username}")
    String from;
    public void sendVerificationCode(String toEmail, String verificationCode) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 将 LocalDateTime 对象格式化为字符串
        String formattedDateTime = now.format(formatter);
//        开始发送邮箱
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);  //必须设置发件人
        message.setTo(toEmail);  // 设置收件人
        message.setSubject("弈宁IO注册验证码");  // 设置邮件主题
        message.setText("您正在注册"+"弈宁IO在线考试平台"+"账号，本次的验证码是："+verificationCode+"\n" +
                "\n" +
                "该验证码 5 分钟内有效，请尽快完成注册。为了保障您的账号安全，请勿将验证码泄露给他人。\n" +
                "\n" +
                "如果这不是您本人的操作，请忽略此邮件。\n" +
                "\n" +
                "弈宁IO\n" +
                formattedDateTime );  // 设置邮件内容
        mailSender.send(message);  // 发送邮件
    }
}