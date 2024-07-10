package com.jxh.lease.common.sms;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: AliyunSMSConfiguration
 * Package: com.jxh.lease.common.sms
 * Description:
 *
 * @author JX
 * @version 1.0
 * @date 2024/7/10 19:25
 */
@Configuration
@EnableConfigurationProperties(AliyunSMSProperties.class)
@ConditionalOnProperty(prefix = "aliyun.sms", name = "endpoint")
public class AliyunSMSConfiguration {

    private final AliyunSMSProperties aliyunSMSProperties;

    public AliyunSMSConfiguration(AliyunSMSProperties aliyunSMSProperties) {
        this.aliyunSMSProperties = aliyunSMSProperties;
    }

    @Bean
    public Client smsClient() {
        Config config = new Config();
        config.setAccessKeyId(aliyunSMSProperties.getAccessKeyId());
        config.setAccessKeySecret(aliyunSMSProperties.getAccessKeySecret());
        config.setEndpoint(aliyunSMSProperties.getEndpoint());
        try {
            return new Client(config);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}