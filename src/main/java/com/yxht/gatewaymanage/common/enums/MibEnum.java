package com.yxht.gatewaymanage.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @Description MIB库
 * @Author fsy
 * @Date 2024/6/6 09:50
 */

@AllArgsConstructor
@Getter
public enum MibEnum {

      DEVICE_TYPE("1.3.6.1.4.1.39871.1.1.1.0", "deviceType", "设备型号"),

      DEVICE_SN("1.3.6.1.4.1.39871.1.1.2.0", "deviceSN", "设备序列号"),

      CPU_TYPE("1.3.6.1.4.1.39871.1.1.8.0", "cpuType", "cpu类型"),

      ISDNLINK_0_STATUS("1.3.6.1.4.1.39871.1.1.16.0", "isdnlink0status", "获取PCM0的ISDN链路状态"),

      SS7LINK_0_STATUS("1.3.6.1.4.1.39871.1.1.79.0", "ss7link0status", "获取PCM0的SS7链路状态"),

      TRUNK_0_STATUS("1.3.6.1.4.1.39871.1.1.142.0", "trunk0status", "获取PCM0的链路值"),

      CUPUSE("1.3.6.1.4.1.39871.1.6.1.0", "cpuuse", "获取CPU占用率"),

      MEMMORYSIZE("1.3.6.1.4.1.39871.1.6.2.0", "memmorysize", "获取内存大小"),

      MEMMORYUSE("1.3.6.1.4.1.39871.1.6.3.0", "memmoryuse", "获取已使用的内存大小"),

      LOCK_ENABLE("1.3.6.1.4.1.39871.1.7.1.0", "lockEnable", "设备是否锁定"),

      LOCK_STATE("1.3.6.1.4.1.39871.1.7.2.0", "lockState", "锁定状态")
      ;

      private final String oid;

      private final String name;

      private final String remark;

      public static MibEnum getValueByOid(String oid) {
            return Arrays.stream(MibEnum.values())
                    .filter(s -> s.getOid().equals(oid))
                    .findFirst()
                    .orElse(null);
      }
}
