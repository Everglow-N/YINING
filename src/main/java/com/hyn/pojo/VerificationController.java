package com.hyn.pojo;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
// 每隔60秒钟自动清理一次过期的验证码
@Component
public class VerificationController {
    private Map<String, VerificationCode> verificationCodes = new HashMap<>();

    /**
     * 每隔1分钟清理一次过期的验证码
     */
    @Scheduled(fixedRate = 600000)  // 60秒
    public void cleanupExpiredCodes() {
        LocalDateTime now = LocalDateTime.now();
        verificationCodes.entrySet().removeIf(entry -> entry.getValue().isExpired());
    }
}
