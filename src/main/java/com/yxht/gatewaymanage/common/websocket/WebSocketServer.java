package com.yxht.gatewaymanage.common.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yxht.gatewaymanage.common.component.SnmpV2c;
import com.yxht.gatewaymanage.common.config.WebSocketConfigurator;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Description TODO
 * @Author fsy
 * @Date 2024/6/12 11:31
 */
@ServerEndpoint(value = "/api/websocket/{userid}", configurator = WebSocketConfigurator.class)
@Component
public class WebSocketServer {

      private String userId;

      private Timer timer;

      @Value("${websocket.period}")
      private Integer period;

      @Value("${websocket.delay}")
      private Integer delay;

      @Resource
      private SnmpV2c snmpV2c;

      @OnOpen
      public void onOpen(Session session, @PathParam("userid") String userId) {
            System.out.println("创建连接");
            this.userId = userId;
            timer = newTimer();
            timer.schedule(new TimerTask() {
                  @SneakyThrows
                  @Override
                  public void run() {
                        String data = new ObjectMapper().writeValueAsString(snmpV2c.get());
                        if (session.isOpen()) {
                              session.getBasicRemote().sendText(data);
                        }
                  }
            }, delay, period);
      }

      @OnClose
      public void onClose() {
            System.out.println("关闭连接");
            closeTimer();
      }

      @OnMessage
      @SneakyThrows
      public void onMessage(Session session, String msg) {
            System.out.println(msg);
            session.getBasicRemote().sendText("收到");
      }

      public Timer newTimer() {
            return new Timer();
      }

      public void closeTimer() {
            timer.cancel();
      }
}
