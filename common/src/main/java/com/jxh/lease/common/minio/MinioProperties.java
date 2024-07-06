package com.jxh.lease.common.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ClassName: MinioProperties
 * Package: com.jxh.lease.common.minio
 * Description:
 *
 * @author JX
 * @version 1.0
 * @date 2024/7/6 21:31
 */
@Data
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {

    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;

}
