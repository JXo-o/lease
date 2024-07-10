package com.jxh.lease.common.utils;

import java.util.Random;

/**
 * ClassName: VerifyCodeUtil
 * Package: com.jxh.lease.common.utils.jwt
 * Description:
 *
 * @author JX
 * @version 1.0
 * @date 2024/7/10 19:46
 */
public class VerifyCodeUtil {
    public static String getVerifyCode(int length) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            builder.append(random.nextInt(10));
        }
        return builder.toString();
    }
}