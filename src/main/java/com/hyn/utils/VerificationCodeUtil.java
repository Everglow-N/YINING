package com.hyn.utils;

import java.util.Random;

public class VerificationCodeUtil {
    /**
     * 生成6位随机验证码
     */
    public static String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);  // 生成100000到999999之间的随机数
        return String.valueOf(code);
    }
}

