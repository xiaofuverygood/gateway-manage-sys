package com.yxht.gatewaymanage.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description Web配置
 * @Author fsy
 * @Date 2024/6/12 10:33
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

      @Override
      public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**") // 允许所有路径
                    .allowedOrigins("*") // 允许的域
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的方法
                    .allowedHeaders("*") // 允许的请求头
                    .allowCredentials(true); // 允许携带凭证
      }



      
}