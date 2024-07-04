package com.jxh.lease.common.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: MybatisPlusConfiguration
 * Package: com.jxh.lease.common.mybatisplus
 * Description:
 *
 * @author JX
 * @version 1.0
 * @date 2024/7/4 16:48
 */
@Configuration
@MapperScan("com.jxh.lease.web.*.mapper")
public class MybatisPlusConfiguration {
}
