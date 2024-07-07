package com.jxh.lease.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;

@Schema(description = "房间&支付方式关联表")
@TableName(value = "room_payment_type")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomPaymentType extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "房间id")
    @TableField(value = "room_id")
    private Long roomId;

    @Schema(description = "支付类型id")
    @TableField(value = "payment_type_id")
    private Long paymentTypeId;


}