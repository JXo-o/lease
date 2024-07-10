package com.jxh.lease;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * ClassName: AppWebApplication
 * Package: com.jxh.lease
 * Description:
 *
 * @author JX
 * @version 1.0
 * @date 2024/7/10 11:55
 */
@EnableAsync
@EnableScheduling
@SpringBootApplication
public class AppWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppWebApplication.class, args);
    }
}
