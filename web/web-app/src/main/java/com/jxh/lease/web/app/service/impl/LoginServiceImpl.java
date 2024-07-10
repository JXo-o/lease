package com.jxh.lease.web.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jxh.lease.common.constant.RedisConstant;
import com.jxh.lease.common.exception.LeaseException;
import com.jxh.lease.common.result.ResultCodeEnum;
import com.jxh.lease.common.utils.VerifyCodeUtil;
import com.jxh.lease.common.utils.jwt.JwtUtil;
import com.jxh.lease.model.entity.UserInfo;
import com.jxh.lease.model.enums.BaseStatus;
import com.jxh.lease.web.app.service.LoginService;
import com.jxh.lease.web.app.service.SmsService;
import com.jxh.lease.web.app.service.UserInfoService;
import com.jxh.lease.web.app.vo.user.LoginVo;
import com.jxh.lease.web.app.vo.user.UserInfoVo;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    private final SmsService smsService;
    private final UserInfoService userInfoService;
    private final StringRedisTemplate stringRedisTemplate;
    private final JwtUtil jwtUtil;

    public LoginServiceImpl(
            SmsService smsService,
            UserInfoService userInfoService,
            StringRedisTemplate stringRedisTemplate,
            JwtUtil jwtUtil
    ) {
        this.smsService = smsService;
        this.userInfoService = userInfoService;
        this.stringRedisTemplate = stringRedisTemplate;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void getSMSCode(String phone) {

        if (!StringUtils.hasText(phone)) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_PHONE_EMPTY);
        }

        String key = RedisConstant.APP_LOGIN_PREFIX + phone;
        boolean hasKey = Boolean.TRUE.equals(stringRedisTemplate.hasKey(key));
        if (hasKey) {
            Long expire = stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
            if (expire == null || RedisConstant.APP_LOGIN_CODE_TTL_SEC - expire < RedisConstant.APP_LOGIN_CODE_RESEND_TIME_SEC) {
                throw new LeaseException(ResultCodeEnum.APP_SEND_SMS_TOO_OFTEN);
            }
        }

        String verifyCode = VerifyCodeUtil.getVerifyCode(6);
        smsService.sendCode(phone, verifyCode);
        stringRedisTemplate.opsForValue().set(key, verifyCode, RedisConstant.APP_LOGIN_CODE_TTL_SEC, TimeUnit.SECONDS);
    }

    @Override
    public String login(LoginVo loginVo) {

        if (!StringUtils.hasText(loginVo.getPhone())) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_PHONE_EMPTY);
        }

        if (!StringUtils.hasText(loginVo.getCode())) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_EMPTY);
        }

        String key = RedisConstant.APP_LOGIN_PREFIX + loginVo.getPhone();
        String code = stringRedisTemplate.opsForValue().get(key);
        if (code == null) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_EXPIRED);
        }

        if (!code.equals(loginVo.getCode())) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_ERROR);
        }

        UserInfo userInfo = userInfoService.lambdaQuery()
                .eq(UserInfo::getPhone, loginVo.getPhone())
                .getEntity();

        if (userInfo == null) {
            UserInfo newUser = UserInfo.builder()
                    .phone(loginVo.getPhone())
                    .status(BaseStatus.ENABLE)
                    .nickname("用户-" + loginVo.getPhone().substring(6))
                    .build();
            userInfoService.save(newUser);
            return jwtUtil.createToken(newUser.getId(), loginVo.getPhone());
        } else {
            if (userInfo.getStatus().equals(BaseStatus.DISABLE)) {
                throw new LeaseException(ResultCodeEnum.APP_ACCOUNT_DISABLED_ERROR);
            }
        }

        return jwtUtil.createToken(userInfo.getId(), loginVo.getPhone());
    }

    @Override
    public UserInfoVo getUserInfoById(Long userId) {
        UserInfo userInfo = userInfoService.getById(userId);
        return UserInfoVo.builder()
                .nickname(userInfo.getNickname())
                .avatarUrl(userInfo.getAvatarUrl())
                .build();
    }

}
