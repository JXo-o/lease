package com.jxh.lease.web.admin.vo.login;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Schema(description = "图像验证码")
@NoArgsConstructor
@AllArgsConstructor
public class CaptchaVo {

    @Schema(description="验证码图片信息")
    private String image;

    @Schema(description="验证码key")
    private String key;
}
