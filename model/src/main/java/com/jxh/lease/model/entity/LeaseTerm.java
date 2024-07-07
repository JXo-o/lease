package com.jxh.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;

/**
 * @TableName lease_term
 */
@Schema(description = "租期信息")
@TableName(value = "lease_term")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeaseTerm extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "租期月数")
    @TableField("month_count")
    private Integer monthCount;

    @Schema(description = "租期单位:月")
    @TableField("unit")
    private String unit;
}