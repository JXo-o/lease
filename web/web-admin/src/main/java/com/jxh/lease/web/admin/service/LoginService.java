package com.jxh.lease.web.admin.service;

import com.jxh.lease.web.admin.vo.login.CaptchaVo;
import com.jxh.lease.web.admin.vo.login.LoginVo;
import com.jxh.lease.web.admin.vo.system.user.SystemUserInfoVo;

public interface LoginService {

    CaptchaVo getCaptchaVo();

    String login(LoginVo loginVo);

    SystemUserInfoVo getSystemUserInfo();
}
