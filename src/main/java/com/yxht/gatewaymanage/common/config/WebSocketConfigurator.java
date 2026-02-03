package com.yxht.gatewaymanage.common.config;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import javax.websocket.server.ServerEndpointConfig.Configurator;

/**
 * @Description WebSocket配置器
 * @Author fsy
 * @Date 2024/6/12 13:39
 */
@Component
public class WebSocketConfigurator extends Configurator {

      private static ApplicationContext applicationContext;

      public static void setApplicationContext(ApplicationContext context) {
            applicationContext = context;
      }

      @Override
      public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
            return applicationContext.getBean(endpointClass);
      }
}