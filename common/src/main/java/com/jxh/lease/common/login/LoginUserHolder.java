package com.jxh.lease.common.login;

/**
 * ClassName: LoginUserHolder
 * Package: com.jxh.lease.common.login
 * Description:
 *
 * @author JX
 * @version 1.0
 * @date 2024/7/9 21:32
 */
public class LoginUserHolder {

    public static ThreadLocal<LoginUser> threadLocal = new ThreadLocal<>();

    public static void setLoginUser(LoginUser loginUser) {
        threadLocal.set(loginUser);
    }

    public static LoginUser getLoginUser() {
        return threadLocal.get();
    }

    public static void clear() {
        threadLocal.remove();
    }

}
