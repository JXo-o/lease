package com.jxh.lease.web.admin.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxh.lease.common.result.Result;
import com.jxh.lease.model.entity.SystemUser;
import com.jxh.lease.model.enums.BaseStatus;
import com.jxh.lease.web.admin.service.SystemUserService;
import com.jxh.lease.web.admin.vo.system.user.SystemUserItemVo;
import com.jxh.lease.web.admin.vo.system.user.SystemUserQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.*;

@Tag(name = "后台用户信息管理")
@RestController
@RequestMapping("/admin/system/user")
public class SystemUserController {

    private final SystemUserService systemUserService;

    public SystemUserController(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }

    @Operation(summary = "根据条件分页查询后台用户列表")
    @GetMapping("page")
    public Result<IPage<SystemUserItemVo>> page(@RequestParam long current, @RequestParam long size, SystemUserQueryVo queryVo) {
        return Result.ok(systemUserService.pageSystemUserItemByQuery(new Page<>(current, size), queryVo));
    }

    @Operation(summary = "根据ID查询后台用户信息")
    @GetMapping("getById")
    public Result<SystemUserItemVo> getById(@RequestParam Long id) {
        return Result.ok(systemUserService.getSystemUserById(id));
    }

    @Operation(summary = "保存或更新后台用户信息")
    @PostMapping("saveOrUpdate")
    public Result<?> saveOrUpdate(@RequestBody SystemUser systemUser) {
        if (systemUser.getPassword() != null) {
            systemUser.setPassword(DigestUtils.md2Hex(systemUser.getPassword()));
        }
        systemUserService.saveOrUpdate(systemUser);
        return Result.ok();
    }

    @Operation(summary = "判断后台用户名是否可用")
    @GetMapping("isUserNameAvailable")
    public Result<Boolean> isUsernameExists(@RequestParam String username) {
        Long count = systemUserService.lambdaQuery()
                .eq(SystemUser::getUsername, username)
                .count();
        return Result.ok(count == 0);
    }

    @DeleteMapping("deleteById")
    @Operation(summary = "根据ID删除后台用户信息")
    public Result<?> removeById(@RequestParam Long id) {
        systemUserService.removeById(id);
        return Result.ok();
    }

    @Operation(summary = "根据ID修改后台用户状态")
    @PostMapping("updateStatusByUserId")
    public Result<?> updateStatusByUserId(@RequestParam Long id, @RequestParam BaseStatus status) {
        systemUserService.lambdaUpdate()
                .set(SystemUser::getStatus, status)
                .eq(SystemUser::getId, id)
                .update(new SystemUser());
        return Result.ok();
    }
}
