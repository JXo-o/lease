package com.jxh.lease.web.admin.service.impl;

import com.jxh.lease.common.redis.RedisConstant;
import com.jxh.lease.common.exception.LeaseException;
import com.jxh.lease.common.login.LoginUser;
import com.jxh.lease.common.login.LoginUserHolder;
import com.jxh.lease.common.result.ResultCodeEnum;
import com.jxh.lease.common.utils.jwt.JwtUtil;
import com.jxh.lease.model.entity.SystemUser;
import com.jxh.lease.model.enums.BaseStatus;
import com.jxh.lease.web.admin.mapper.SystemUserMapper;
import com.jxh.lease.web.admin.service.LoginService;
import com.jxh.lease.web.admin.vo.login.CaptchaVo;
import com.jxh.lease.web.admin.vo.login.LoginVo;
import com.jxh.lease.web.admin.vo.system.user.SystemUserInfoVo;
import com.wf.captcha.SpecCaptcha;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    private final SystemUserMapper systemUserMapper;
    private final StringRedisTemplate stringRedisTemplate;
    private final JwtUtil jwtUtil;

    public LoginServiceImpl(
            SystemUserMapper systemUserMapper,
            StringRedisTemplate stringRedisTemplate,
            JwtUtil jwtUtil
    ) {
        this.systemUserMapper = systemUserMapper;
        this.stringRedisTemplate = stringRedisTemplate;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public CaptchaVo getCaptchaVo() {

        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 4);
        String verCode = specCaptcha.text().toLowerCase();
        String key = RedisConstant.ADMIN_LOGIN_PREFIX + UUID.randomUUID();
        stringRedisTemplate.opsForValue().set(key, verCode, RedisConstant.ADMIN_LOGIN_CAPTCHA_TTL_SEC, TimeUnit.SECONDS);

        return CaptchaVo.builder().key(key).image(specCaptcha.toBase64()).build();
    }

    @Override
    public String login(LoginVo loginVo) {

        if (loginVo.getCaptchaCode() == null) {
            throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_NOT_FOUND);
        }

        String verCode = stringRedisTemplate.opsForValue().get(loginVo.getCaptchaKey());
        if (verCode == null) {
            throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_EXPIRED);
        }

        if (!verCode.equals(loginVo.getCaptchaCode().toLowerCase())) {
            throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_ERROR);
        }

        SystemUser systemUser = systemUserMapper.selectOneByUsername(loginVo.getUsername());
        if (systemUser == null) {
            throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_NOT_EXIST_ERROR);
        }

        if (systemUser.getStatus() == BaseStatus.DISABLE) {
            throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_DISABLED_ERROR);
        }

        if (!DigestUtils.md2Hex(loginVo.getPassword()).equals(systemUser.getPassword())) {
            throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_ERROR);
        }

        return jwtUtil.createToken(systemUser.getId(), systemUser.getUsername());

    }

    @Override
    public SystemUserInfoVo getSystemUserInfo() {

        LoginUser loginUser = LoginUserHolder.getLoginUser();
        SystemUserInfoVo systemUserInfoVo = new SystemUserInfoVo();
        BeanUtils.copyProperties(systemUserMapper.selectById(loginUser.getUserId()), systemUserInfoVo);

        return systemUserInfoVo;
    }

}
