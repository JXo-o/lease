package com.jxh.lease.web.app.controller.leasaterm;

import com.jxh.lease.common.result.Result;
import com.jxh.lease.model.entity.LeaseTerm;
import com.jxh.lease.web.app.service.LeaseTermService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/app/term/")
@Tag(name = "租期信息")
public class LeaseTermController {

    private final LeaseTermService leaseTermService;

    public LeaseTermController(LeaseTermService leaseTermService) {
        this.leaseTermService = leaseTermService;
    }

    @GetMapping("listByRoomId")
    @Operation(summary = "根据房间id获取可选获取租期列表")
    public Result<List<LeaseTerm>> list(@RequestParam Long id) {
        return Result.ok(leaseTermService.listByRoomId(id));
    }
}
