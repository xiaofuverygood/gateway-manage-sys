package com.yxht.gatewaymanage.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Description TODO
 * @Author fsy
 * @Date 2024/6/13 13:28
 */
@Configuration
public class WebSocketConfig {
      @Bean
      public ServerEndpointExporter serverEndpointExporter() {
            return new ServerEndpointExporter();
      }
}
