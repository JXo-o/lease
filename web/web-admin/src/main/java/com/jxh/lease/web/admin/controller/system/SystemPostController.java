package com.jxh.lease.web.admin.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxh.lease.common.result.Result;
import com.jxh.lease.model.entity.SystemPost;
import com.jxh.lease.model.enums.BaseStatus;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jxh.lease.web.admin.service.SystemPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "后台用户岗位管理")
@RequestMapping("/admin/system/post")
public class SystemPostController {

    private final SystemPostService systemPostService;

    public SystemPostController(SystemPostService systemPostService) {
        this.systemPostService = systemPostService;
    }

    @Operation(summary = "分页获取岗位信息")
    @GetMapping("page")
    private Result<IPage<SystemPost>> page(@RequestParam long current, @RequestParam long size) {
        return Result.ok(systemPostService.page(new Page<>(current, size)));
    }

    @Operation(summary = "保存或更新岗位信息")
    @PostMapping("saveOrUpdate")
    public Result<?> saveOrUpdate(@RequestBody SystemPost systemPost) {
        systemPostService.saveOrUpdate(systemPost);
        return Result.ok();
    }

    @DeleteMapping("deleteById")
    @Operation(summary = "根据id删除岗位")
    public Result<?> removeById(@RequestParam Long id) {
        systemPostService.removeById(id);
        return Result.ok();
    }

    @GetMapping("getById")
    @Operation(summary = "根据id获取岗位信息")
    public Result<SystemPost> getById(@RequestParam Long id) {
        return Result.ok(systemPostService.getById(id));
    }

    @Operation(summary = "获取全部岗位列表")
    @GetMapping("list")
    public Result<List<SystemPost>> list() {
        return Result.ok(systemPostService.list());
    }

    @Operation(summary = "根据岗位id修改状态")
    @PostMapping("updateStatusByPostId")
    public Result<?> updateStatusByPostId(@RequestParam Long id, @RequestParam BaseStatus status) {
        systemPostService.lambdaUpdate()
                .set(SystemPost::getStatus, status)
                .eq(SystemPost::getId, id)
                .update(new SystemPost());
        return Result.ok();
    }
}
