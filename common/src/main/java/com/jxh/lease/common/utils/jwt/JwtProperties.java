package com.jxh.lease.common.utils.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * ClassName: JwtProperties
 * Package: com.jxh.lease.common.utils.jwt
 * Description:
 *
 * @author JX
 * @version 1.0
 * @date 2024/7/9 20:08
 */
@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private Duration expiration;
    private String signKey;

}
