package com.yxht.gatewaymanage;

import com.yxht.gatewaymanage.common.config.WebSocketConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @Description TODO
 * @Author fsy
 * @Date 2024/6/5 11:40
 */
@SpringBootApplication
public class GMApplication {
    public static void main(String[] args) {
        // SpringApplication.run(SnmpApplication.class, args);
        ApplicationContext context = SpringApplication.run(GMApplication.class, args);
        WebSocketConfigurator.setApplicationContext(context);
    }
}