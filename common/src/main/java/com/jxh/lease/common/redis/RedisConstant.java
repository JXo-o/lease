package com.jxh.lease.common.redis;

/**
 * ClassName: RedisConstant
 * Package: com.jxh.lease.common.constant
 * Description:
 *
 * @author JX
 * @version 1.0
 * @date 2024/7/9 15:12
 */
public class RedisConstant {

    public static final String ADMIN_LOGIN_PREFIX = "admin:login:";
    public static final Integer ADMIN_LOGIN_CAPTCHA_TTL_SEC = 60;
    public static final String APP_LOGIN_PREFIX = "app:login:";
    public static final Integer APP_LOGIN_CODE_RESEND_TIME_SEC = 60;
    public static final Integer APP_LOGIN_CODE_TTL_SEC = 60 * 10;
    public static final String APP_ROOM_PREFIX = "app:room:";
    public static final String APP_APARTMENT_PREFIX = "app:apartment:";
    public static final String APP_APPOINTMENT_PREFIX = "app:appointment:";
    public static final String APP_AGREEMENT_PREFIX = "app:agreement:";
    public static final Integer DEFAULT_EXPIRE_TIME = 60 * 30;

}
