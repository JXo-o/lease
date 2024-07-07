package com.jxh.lease.web.admin.controller.apartment;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxh.lease.common.result.Result;
import com.jxh.lease.model.entity.ApartmentInfo;
import com.jxh.lease.model.enums.ReleaseStatus;
import com.jxh.lease.web.admin.service.ApartmentInfoService;
import com.jxh.lease.web.admin.vo.apartment.ApartmentDetailVo;
import com.jxh.lease.web.admin.vo.apartment.ApartmentItemVo;
import com.jxh.lease.web.admin.vo.apartment.ApartmentQueryVo;
import com.jxh.lease.web.admin.vo.apartment.ApartmentSubmitVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "公寓信息管理")
@RestController
@RequestMapping("/admin/apartment")
public class ApartmentController {

    private final ApartmentInfoService apartmentInfoService;

    public ApartmentController(ApartmentInfoService apartmentInfoService) {
        this.apartmentInfoService = apartmentInfoService;
    }

    @Operation(summary = "保存或更新公寓信息")
    @PostMapping("saveOrUpdate")
    public Result<?> saveOrUpdate(@RequestBody ApartmentSubmitVo apartmentSubmitVo) {
        apartmentInfoService.saveOrUpdateApartment(apartmentSubmitVo);
        return Result.ok();
    }

    @Operation(summary = "根据条件分页查询公寓列表")
    @GetMapping("pageItem")
    public Result<IPage<ApartmentItemVo>> pageItem(@RequestParam long current, @RequestParam long size, ApartmentQueryVo queryVo) {
        return Result.ok(apartmentInfoService.pageApartmentItemByQuery(new Page<>(current, size), queryVo));
    }

    @Operation(summary = "根据ID获取公寓详细信息")
    @GetMapping("getDetailById")
    public Result<ApartmentDetailVo> getDetailById(@RequestParam Long id) {
        return Result.ok(apartmentInfoService.getDetailById(id));
    }

    @Operation(summary = "根据id删除公寓信息")
    @DeleteMapping("removeById")
    public Result<?> removeById(@RequestParam Long id) {
        apartmentInfoService.removeApartmentById(id);
        return Result.ok();
    }

    @Operation(summary = "根据id修改公寓发布状态")
    @PostMapping("updateReleaseStatusById")
    public Result<?> updateReleaseStatusById(@RequestParam Long id, @RequestParam ReleaseStatus status) {
        apartmentInfoService.lambdaUpdate()
                .set(ApartmentInfo::getIsRelease, status)
                .eq(ApartmentInfo::getId, id)
                .update(new ApartmentInfo());
        return Result.ok();
    }

    @Operation(summary = "根据区县id查询公寓信息列表")
    @GetMapping("listInfoByDistrictId")
    public Result<List<ApartmentInfo>> listInfoByDistrictId(@RequestParam Long id) {
        List<ApartmentInfo> list = apartmentInfoService.lambdaQuery()
                .eq(ApartmentInfo::getDistrictId, id)
                .list();
        return Result.ok(list);
    }
}














