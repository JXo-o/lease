package com.jxh.lease.web.app.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "用户基本信息")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVo {

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "用户头像")
    private String avatarUrl;

}
