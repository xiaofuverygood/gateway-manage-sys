package com.yxht.gatewaymanage.controller;

import com.yxht.gatewaymanage.common.component.SnmpV2c;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description TODO
 * @Author fsy
 * @Date 2024/6/11 09:36
 */
@RequestMapping("snmp")
@RestController
@RequiredArgsConstructor
@Tag(name = "网关管理系统")
public class SnmpController {

      private final SnmpV2c snmpV2c;

      @Operation(summary = "获取设备信息")
      @Parameters({
              @Parameter(name = "userID",description = "用户ID", in = ParameterIn.QUERY),
      })
      @ApiResponses(value = {
              @ApiResponse(responseCode = "200", description = "Successful", content = {
                      @Content(mediaType = "application/json", schema = @Schema(implementation = SnmpV2c.Node.class))
              })
      })
      @GetMapping("netWorkManagementSysInfo")
      public List<SnmpV2c.Node> netWorkManagementSysInfo() {
            return snmpV2c.get();
      }
}
