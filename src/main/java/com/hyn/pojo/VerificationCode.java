package com.hyn.pojo;

import java.time.LocalDateTime;

//此类用于存储对应的验证码和过期时间!!!!!!!!!!!!!
public class VerificationCode {
    private String code;  // 验证码
    private LocalDateTime expiryTime;  // 过期时间

    public VerificationCode(String code, LocalDateTime expiryTime) {
        this.code = code;
        this.expiryTime = expiryTime;
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    /**
     * 检查验证码是否过期
     */
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiryTime);
    }
}
