package com.jxh.lease.common.sms;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ClassName: AliyunSMSProperties
 * Package: com.jxh.lease.common.sms
 * Description:
 *
 * @author JX
 * @version 1.0
 * @date 2024/7/10 19:22
 */
@Data
@ConfigurationProperties(prefix = "aliyun.sms")
public class AliyunSMSProperties {

    private String accessKeyId;

    private String accessKeySecret;

    private String endpoint;
}