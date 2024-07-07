package com.jxh.lease.model.entity;

import com.jxh.lease.model.enums.BaseStatus;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;

/**
 * 岗位信息表
 *
 * @TableName system_post
 */
@TableName(value = "system_post")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemPost extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "岗位编码")
    @TableField(value = "code")
    private String postCode;

    @Schema(description = "岗位名称")
    @TableField(value = "name")
    private String name;

    @Schema(description = "岗位描述信息")
    @TableField(value = "description")
    private String description;

    @Schema(description = "岗位状态")
    @TableField(value = "status")
    private BaseStatus status;


}