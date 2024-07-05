package com.jxh.lease.web.admin.controller.apartment;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jxh.lease.common.result.Result;
import com.jxh.lease.model.entity.FacilityInfo;
import com.jxh.lease.model.enums.ItemType;
import com.jxh.lease.web.admin.service.FacilityInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "配套管理")
@RestController
@RequestMapping("/admin/facility")
public class FacilityController {

    private final FacilityInfoService facilityInfoService;

    public FacilityController(FacilityInfoService facilityInfoService) {
        this.facilityInfoService = facilityInfoService;
    }

    @Operation(summary = "[根据类型]查询配套信息列表")
    @GetMapping("list")
    public Result<List<FacilityInfo>> listFacility(@RequestParam(required = false) ItemType type) {
        LambdaQueryWrapper<FacilityInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(type != null, FacilityInfo::getType, type);
        return Result.ok(facilityInfoService.list(lambdaQueryWrapper));
    }

    @Operation(summary = "新增或修改配套信息")
    @PostMapping("saveOrUpdate")
    public Result<?> saveOrUpdate(@RequestBody FacilityInfo facilityInfo) {
        facilityInfoService.saveOrUpdate(facilityInfo);
        return Result.ok();
    }

    @Operation(summary = "根据id删除配套信息")
    @DeleteMapping("deleteById")
    public Result<?> removeFacilityById(@RequestParam Long id) {
        facilityInfoService.removeById(id);
        return Result.ok();
    }

}
