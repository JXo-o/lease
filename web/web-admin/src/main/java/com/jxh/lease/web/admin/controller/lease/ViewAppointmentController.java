package com.jxh.lease.web.admin.controller.lease;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxh.lease.common.result.Result;
import com.jxh.lease.model.entity.ViewAppointment;
import com.jxh.lease.model.enums.AppointmentStatus;
import com.jxh.lease.web.admin.service.ViewAppointmentService;
import com.jxh.lease.web.admin.vo.appointment.AppointmentQueryVo;
import com.jxh.lease.web.admin.vo.appointment.AppointmentVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "预约看房管理")
@RequestMapping("/admin/appointment")
@RestController
public class ViewAppointmentController {

    private final ViewAppointmentService viewAppointmentService;

    public ViewAppointmentController(ViewAppointmentService viewAppointmentService) {
        this.viewAppointmentService = viewAppointmentService;
    }

    @Operation(summary = "分页查询预约信息")
    @GetMapping("page")
    public Result<IPage<AppointmentVo>> page(@RequestParam long current, @RequestParam long size, AppointmentQueryVo queryVo) {
        return Result.ok(viewAppointmentService.pageAppointmentByQuery(new Page<>(current, size), queryVo));
    }

    @Operation(summary = "根据id更新预约状态")
    @PostMapping("updateStatusById")
    public Result<?> updateStatusById(@RequestParam Long id, @RequestParam AppointmentStatus status) {
        viewAppointmentService.lambdaUpdate()
                .set(ViewAppointment::getAppointmentStatus, status)
                .eq(ViewAppointment::getId, id)
                .update(new ViewAppointment());
        return Result.ok();
    }

}
