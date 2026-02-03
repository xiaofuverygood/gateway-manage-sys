package com.yxht.gatewaymanage;

import com.yxht.gatewaymanage.common.component.SnmpV2c;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description TODO
 * @Author fsy
 * @Date 2024/6/6 08:42
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class SnmpV2CTest {

      @Autowired
      private SnmpV2c snmpV2C;

      @Test
      public void test() {
            snmpV2C.get().forEach(System.out::println);
      }
}
