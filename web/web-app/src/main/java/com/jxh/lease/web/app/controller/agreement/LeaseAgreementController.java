package com.jxh.lease.web.app.controller.agreement;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.jxh.lease.common.login.LoginUserHolder;
import com.jxh.lease.common.result.Result;
import com.jxh.lease.model.entity.LeaseAgreement;
import com.jxh.lease.model.enums.LeaseStatus;
import com.jxh.lease.web.app.service.LeaseAgreementService;
import com.jxh.lease.web.app.vo.agreement.AgreementDetailVo;
import com.jxh.lease.web.app.vo.agreement.AgreementItemVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/agreement")
@Tag(name = "租约信息")
public class LeaseAgreementController {

    private final LeaseAgreementService leaseAgreementService;

    public LeaseAgreementController(LeaseAgreementService leaseAgreementService) {
        this.leaseAgreementService = leaseAgreementService;
    }

    @Operation(summary = "获取个人租约基本信息列表")
    @GetMapping("listItem")
    public Result<List<AgreementItemVo>> listItem() {
        return Result.ok(leaseAgreementService.listItemByPhone(LoginUserHolder.getLoginUser().getUsername()));
    }

    @Operation(summary = "根据id获取租约详细信息")
    @GetMapping("getDetailById")
    public Result<AgreementDetailVo> getDetailById(@RequestParam Long id) {
        return Result.ok(leaseAgreementService.getDetailById(id));
    }

    @Operation(summary = "根据id更新租约状态", description = "用于确认租约和提前退租")
    @PostMapping("updateStatusById")
    public Result<?> updateStatusById(@RequestParam Long id, @RequestParam LeaseStatus leaseStatus) {
        leaseAgreementService.lambdaUpdate()
                .eq(LeaseAgreement::getId, id)
                .set(LeaseAgreement::getStatus, leaseStatus)
                .update(new LeaseAgreement());
        return Result.ok();
    }

    @Operation(summary = "保存或更新租约", description = "用于续约")
    @PostMapping("saveOrUpdate")
    public Result<?> saveOrUpdate(@RequestBody LeaseAgreement leaseAgreement) {
        leaseAgreementService.saveOrUpdate(leaseAgreement);
        return Result.ok();
    }

}
