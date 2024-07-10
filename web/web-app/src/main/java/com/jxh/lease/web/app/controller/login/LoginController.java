package com.jxh.lease.web.app.controller.login;

import com.jxh.lease.common.login.LoginUserHolder;
import com.jxh.lease.common.result.Result;
import com.jxh.lease.web.app.service.LoginService;
import com.jxh.lease.web.app.vo.user.LoginVo;
import com.jxh.lease.web.app.vo.user.UserInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "登录管理")
@RestController
@RequestMapping("/app/")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("login/getCode")
    @Operation(summary = "获取短信验证码")
    public Result<?> getCode(@RequestParam String phone) {
        loginService.getSMSCode(phone);
        return Result.ok();
    }

    @PostMapping("login")
    @Operation(summary = "登录")
    public Result<String> login(@RequestBody LoginVo loginVo) {
        return Result.ok(loginService.login(loginVo));
    }

    @GetMapping("info")
    @Operation(summary = "获取登录用户信息")
    public Result<UserInfoVo> info() {
        return Result.ok(loginService.getUserInfoById(LoginUserHolder.getLoginUser().getUserId()));
    }
}

