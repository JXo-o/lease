package com.jxh.lease.web.app.controller.appointment;

import com.jxh.lease.common.login.LoginUserHolder;
import com.jxh.lease.common.result.Result;
import com.jxh.lease.model.entity.ViewAppointment;
import com.jxh.lease.web.app.service.ViewAppointmentService;
import com.jxh.lease.web.app.vo.appointment.AppointmentDetailVo;
import com.jxh.lease.web.app.vo.appointment.AppointmentItemVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "看房预约信息")
@RestController
@RequestMapping("/app/appointment")
public class ViewAppointmentController {

    private final ViewAppointmentService viewAppointmentService;

    public ViewAppointmentController(ViewAppointmentService viewAppointmentService) {
        this.viewAppointmentService = viewAppointmentService;
    }

    @Operation(summary = "保存或更新看房预约")
    @PostMapping("/saveOrUpdate")
    public Result<?> saveOrUpdate(@RequestBody ViewAppointment viewAppointment) {
        System.out.println(viewAppointment);
        viewAppointment.setUserId(LoginUserHolder.getLoginUser().getUserId());
        viewAppointmentService.saveOrUpdate(viewAppointment);
        return Result.ok();
    }

    @Operation(summary = "查询个人预约看房列表")
    @GetMapping("listItem")
    public Result<List<AppointmentItemVo>> listItem() {
        return Result.ok(viewAppointmentService.listItemByUserId(LoginUserHolder.getLoginUser().getUserId()));
    }

    @GetMapping("getDetailById")
    @Operation(summary = "根据ID查询预约详情信息")
    public Result<AppointmentDetailVo> getDetailById(Long id) {
        return Result.ok(viewAppointmentService.getDetailById(id));
    }

}

