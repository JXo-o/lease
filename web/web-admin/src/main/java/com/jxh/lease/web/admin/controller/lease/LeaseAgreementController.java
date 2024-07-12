package com.jxh.lease.web.admin.controller.lease;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxh.lease.common.redis.RedisConstant;
import com.jxh.lease.common.result.Result;
import com.jxh.lease.model.entity.LeaseAgreement;
import com.jxh.lease.model.enums.LeaseStatus;
import com.jxh.lease.web.admin.service.LeaseAgreementService;
import com.jxh.lease.web.admin.vo.agreement.AgreementQueryVo;
import com.jxh.lease.web.admin.vo.agreement.AgreementVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Tag(name = "租约管理")
@RestController
@Transactional
@RequestMapping("/admin/agreement")
public class LeaseAgreementController {

    private final LeaseAgreementService leaseAgreementService;
    private final RedisTemplate<String, Object> stringObjectRedisTemplate;

    public LeaseAgreementController(
            LeaseAgreementService leaseAgreementService,
            RedisTemplate<String, Object> stringObjectRedisTemplate
    ) {
        this.leaseAgreementService = leaseAgreementService;
        this.stringObjectRedisTemplate = stringObjectRedisTemplate;
    }

    @Operation(summary = "保存或修改租约信息")
    @PostMapping("saveOrUpdate")
    public Result<?> saveOrUpdate(@RequestBody LeaseAgreement leaseAgreement) {
        leaseAgreementService.saveOrUpdate(leaseAgreement);
        stringObjectRedisTemplate.delete(RedisConstant.APP_AGREEMENT_PREFIX + leaseAgreement.getId());
        return Result.ok();
    }

    @Operation(summary = "根据条件分页查询租约列表")
    @GetMapping("page")
    public Result<IPage<AgreementVo>> page(@RequestParam long current, @RequestParam long size, AgreementQueryVo queryVo) {
        return Result.ok(leaseAgreementService.pageAgreementByQuery(new Page<>(current, size), queryVo));
    }

    @Operation(summary = "根据id查询租约信息")
    @GetMapping(name = "getById")
    public Result<AgreementVo> getById(@RequestParam Long id) {
        return Result.ok(leaseAgreementService.getAgreementById(id));
    }

    @Operation(summary = "根据id删除租约信息")
    @DeleteMapping("removeById")
    public Result<?> removeById(@RequestParam Long id) {
        leaseAgreementService.lambdaUpdate()
                .eq(LeaseAgreement::getId, id)
                .remove();
        stringObjectRedisTemplate.delete(RedisConstant.APP_AGREEMENT_PREFIX + id);
        return Result.ok();
    }

    /**
     * 根据id更新租约状态
     * 注：传入实体对象，默认会更新实体对象中不为null的字段
     * 自动填充需要实体的传入，否则不生效
     * @param id 租约id
     * @param status 租约状态
     * @return Result
     */
    @Operation(summary = "根据id更新租约状态")
    @PostMapping("updateStatusById")
    public Result<?> updateStatusById(@RequestParam Long id, @RequestParam LeaseStatus status) {
        leaseAgreementService.lambdaUpdate()
                .set(LeaseAgreement::getStatus, status)
                .eq(LeaseAgreement::getId, id)
                .update(new LeaseAgreement());
        stringObjectRedisTemplate.delete(RedisConstant.APP_AGREEMENT_PREFIX + id);
        return Result.ok();
    }

}

