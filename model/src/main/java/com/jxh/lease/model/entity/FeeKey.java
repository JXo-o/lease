package com.jxh.lease.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;

@Schema(description = "杂项费用名称表")
@TableName(value = "fee_key")
@Data
public class FeeKey extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "付款项key")
    @TableField(value = "name")
    private String name;


}