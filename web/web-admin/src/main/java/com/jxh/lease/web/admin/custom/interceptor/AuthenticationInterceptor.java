package com.jxh.lease.web.admin.custom.interceptor;

import com.jxh.lease.common.login.LoginUser;
import com.jxh.lease.common.login.LoginUserHolder;
import com.jxh.lease.common.utils.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * ClassName: AuthenticationInterceptor
 * Package: com.jxh.lease.web.admin.custom.interceptor
 * Description:
 *
 * @author JX
 * @version 1.0
 * @date 2024/7/9 21:03
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    public AuthenticationInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler
    ) {
        String token = request.getHeader("access-token");
        Claims claims = jwtUtil.parseToken(token);
        LoginUser loginUser = LoginUser.builder()
                .userId(claims.get("userId", Long.class))
                .username(claims.get("username", String.class))
                .build();
        LoginUserHolder.setLoginUser(loginUser);
        return true;
    }

    @Override
    public void postHandle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler,
            ModelAndView modelAndView
    ) {
        LoginUserHolder.clear();
    }

}
