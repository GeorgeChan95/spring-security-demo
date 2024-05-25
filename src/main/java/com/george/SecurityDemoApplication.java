package com.george;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p></p>
 *
 * @author George
 * @date 2024.05.25 09:27
 */
@SpringBootApplication
public class SecurityDemoApplication {
    public static final Logger LOGGER = LoggerFactory.getLogger(SecurityDemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SecurityDemoApplication.class, args);
        LOGGER.info("SpringSecurityDemo 项目启动成功");
    }
}
