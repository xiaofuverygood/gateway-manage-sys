package com.yxht.gatewaymanage.common.component;

import com.yxht.gatewaymanage.common.enums.MibEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SnmpV2c {

      @Value("${snmp.ip}")
      private String ip;

      @Value("${snmp.port}")
      private String port;

      @Value("${snmp.community}")
      private String community;

      @Value("${snmp.retry-times}")
      private Integer retries;

      @Value("${snmp.timeout}")
      private Integer timeout;

      @Data
      @AllArgsConstructor
      public static class Node {
            @Schema(description = "设备属性")
            private String key;

            @Schema(description = "设备属性值")
            private String value;
      }

      public List<Node> get() {
            List<Node> nodes = new ArrayList<>();
            try {
                  Address target = GenericAddress.parse("udp:" + ip + "/" + port);
                  System.out.println(target);
                  // 创建 TransportMapping
                  TransportMapping<UdpAddress> transport = new DefaultUdpTransportMapping();
                  transport.listen();

                  // 创建 Snmp 对象
                  Snmp snmp = new Snmp(transport);

                  // 创建并配置 CommunityTarget
                  CommunityTarget communityTarget = new CommunityTarget();
                  communityTarget.setCommunity(new OctetString(community));
                  communityTarget.setAddress(target);
                  communityTarget.setRetries(retries);
                  communityTarget.setTimeout(timeout);
                  communityTarget.setVersion(SnmpConstants.version2c);

                  // 创建 PDU
                  PDU pdu = new PDU();
                  pdu.setType(PDU.GET);
                  Arrays.stream(MibEnum.values()).forEach(s -> pdu.add(new VariableBinding(new OID(s.getOid()))));
                  ResponseEvent response = snmp.get(pdu, communityTarget);

                  System.out.println("response" + response);
                  // 处理响应
                  if (response == null) {
                        System.out.println("Error: No response from target");
                  } else {
                        PDU responsePDU = response.getResponse();
                        if (responsePDU == null) {

                        } else if (responsePDU.getErrorStatus() != PDU.noError) {
                              System.out.println("Error: " + responsePDU.getErrorStatusText());

                        } else {
                              System.out.println("Response PDU received from " + response.getPeerAddress());

                              responsePDU.getVariableBindings().forEach(s -> {
                                    String oid = s.getOid().toString();
                                    nodes.add(new Node(MibEnum.getValueByOid(oid).getName(), s.getVariable().toString()));
                              });
                        }
                  }

                  // 关闭 Snmp 对象
                  snmp.close();
            } catch (Exception e) {
                  e.printStackTrace();
            }

            return nodes;
      }
}