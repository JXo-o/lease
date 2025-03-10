package com.jxh.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.util.Date;

/**
 * @TableName browsing_history
 */
@TableName(value = "browsing_history")
@Data
public class BrowsingHistory extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户id")
    @TableField("user_id")
    private Long userId;

    @Schema(description = "房间id")
    @TableField("room_id")
    private Long roomId;

    @Schema(description = "浏览时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("browse_time")
    private Date browseTime;

}