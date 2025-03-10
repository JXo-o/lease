package com.jxh.lease.web.admin.controller.apartment;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jxh.lease.common.result.Result;
import com.jxh.lease.model.entity.LabelInfo;
import com.jxh.lease.model.enums.ItemType;
import com.jxh.lease.web.admin.service.LabelInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "标签管理")
@RestController
@RequestMapping("/admin/label")
public class LabelController {

    private final LabelInfoService labelInfoService;

    public LabelController(LabelInfoService labelInfoService) {
        this.labelInfoService = labelInfoService;
    }

    @Operation(summary = "（根据类型）查询标签列表")
    @GetMapping("list")
    public Result<List<LabelInfo>> labelList(@RequestParam(required = false) ItemType type) {
        LambdaQueryWrapper<LabelInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(type != null, LabelInfo::getType, type);
        return Result.ok(labelInfoService.list(lambdaQueryWrapper));
    }

    @Operation(summary = "新增或修改标签信息")
    @PostMapping("saveOrUpdate")
    public Result<?> saveOrUpdateLabel(@RequestBody LabelInfo labelInfo) {
        labelInfoService.saveOrUpdate(labelInfo);
        return Result.ok();
    }

    @Operation(summary = "根据id删除标签信息")
    @DeleteMapping("deleteById")
    public Result<?> deleteLabelById(@RequestParam Long id) {
        labelInfoService.removeById(id);
        return Result.ok();
    }
}
