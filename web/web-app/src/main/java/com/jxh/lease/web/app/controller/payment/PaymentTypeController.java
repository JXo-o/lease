package com.jxh.lease.web.app.controller.payment;

import com.jxh.lease.common.result.Result;
import com.jxh.lease.model.entity.PaymentType;
import com.jxh.lease.web.app.service.PaymentTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "支付方式接口")
@RestController
@RequestMapping("/app/payment")
public class PaymentTypeController {

    private final PaymentTypeService paymentTypeService;

    public PaymentTypeController(PaymentTypeService paymentTypeService) {
        this.paymentTypeService = paymentTypeService;
    }

    @Operation(summary = "根据房间id获取可选支付方式列表")
    @GetMapping("listByRoomId")
    public Result<List<PaymentType>> list(@RequestParam Long id) {
        return Result.ok(paymentTypeService.listByRoomId(id));
    }

    @Operation(summary = "获取全部支付方式列表")
    @GetMapping("list")
    public Result<List<PaymentType>> list() {
        return Result.ok(paymentTypeService.list());
    }
}
