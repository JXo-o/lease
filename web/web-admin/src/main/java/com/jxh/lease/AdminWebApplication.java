package com.jxh.lease;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * ClassName: AdminWebApplication
 * Package: com.jxh.lease
 * Description:
 *
 * @author JX
 * @version 1.0
 * @date 2024/7/4 16:28
 */
@EnableScheduling
@SpringBootApplication
public class AdminWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminWebApplication.class, args);
    }
}
