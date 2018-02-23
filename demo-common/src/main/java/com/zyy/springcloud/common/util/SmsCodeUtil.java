package com.zyy.springcloud.common.util;

import java.util.Random;

public class SmsCodeUtil {
    private static final int min = 1000;
    private static final int max = 9999;

    /**
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     * @Title: getSmsCode
     * @Description: 生成4位验证码
     */
    public static String getSmsCode() {
        Random rand = new Random();
        int tmp = Math.abs(rand.nextInt());
        return String.valueOf(tmp % (max - min + 1) + min);
    }
}
