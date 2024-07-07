package com.jxh.lease.model.entity;

import com.jxh.lease.model.enums.BaseStatus;
import com.jxh.lease.model.enums.SystemUserType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;

@Schema(description = "员工信息")
@TableName(value = "system_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemUser extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户名")
    @TableField(value = "username")
    private String username;

    @Schema(description = "密码")
    @TableField(value = "password")
    private String password;

    @Schema(description = "姓名")
    @TableField(value = "name")
    private String name;

    @Schema(description = "用户类型")
    @TableField(value = "type")
    private SystemUserType type;

    @Schema(description = "手机号码")
    @TableField(value = "phone")
    private String phone;

    @Schema(description = "头像地址")
    @TableField(value = "avatar_url")
    private String avatarUrl;

    @Schema(description = "备注信息")
    @TableField(value = "additional_info")
    private String additionalInfo;

    @Schema(description = "岗位id")
    @TableField(value = "post_id")
    private Long postId;

    @Schema(description = "账号状态")
    @TableField(value = "status")
    private BaseStatus status;


}