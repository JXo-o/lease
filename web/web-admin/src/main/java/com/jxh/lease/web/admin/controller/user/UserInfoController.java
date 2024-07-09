package com.jxh.lease.web.admin.controller.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxh.lease.common.result.Result;
import com.jxh.lease.model.entity.UserInfo;
import com.jxh.lease.model.enums.BaseStatus;
import com.jxh.lease.web.admin.service.UserInfoService;
import com.jxh.lease.web.admin.vo.user.UserInfoQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户信息管理")
@RestController
@RequestMapping("/admin/user")
public class UserInfoController {

    private final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Operation(summary = "分页查询用户信息")
    @GetMapping("page")
    public Result<IPage<UserInfo>> pageUserInfo(@RequestParam long current, @RequestParam long size, UserInfoQueryVo queryVo) {
        IPage<UserInfo> userInfoIPage = userInfoService.lambdaQuery()
                .eq(queryVo.getStatus() != null, UserInfo::getStatus, queryVo.getStatus())
                .like(queryVo.getPhone() != null && !queryVo.getPhone().isEmpty(), UserInfo::getPhone, queryVo.getPhone())
                .page(new Page<>(current, size));
        // userInfoIPage = userInfoService.pageUserInfoByQuery(new Page<>(current, size), queryVo);
        return Result.ok(userInfoIPage);
    }

    @Operation(summary = "根据用户id更新账号状态")
    @PostMapping("updateStatusById")
    public Result<?> updateStatusById(@RequestParam Long id, @RequestParam BaseStatus status) {
        userInfoService.lambdaUpdate()
                .set(UserInfo::getStatus, status)
                .eq(UserInfo::getId, id)
                .update(new UserInfo());
        return Result.ok();
    }
}
