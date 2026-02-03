<template>
  <div class="app-container">
    <el-row>
      <el-col :span="12" class="card-box">
        <el-card>
          <template #header>
            <Cpu style="width: 1em; height: 1em; vertical-align: middle;"/>
            <span style="vertical-align: middle;">系统信息</span></template>
          <div class="el-table el-table--enable-row-hover el-table--medium">
            <table cellspacing="0" style="width: 100%;">
              <thead>
              <tr>
                <th class="el-table__cell is-leaf">
                  <div class="cell">属性</div>
                </th>
                <th class="el-table__cell is-leaf">
                  <div class="cell">值</div>
                </th>
              </tr>
              </thead>
              <tbody>
              <tr>
                <td class="el-table__cell is-leaf">
                  <div class="cell">设备类型</div>
                </td>
                <td class="el-table__cell is-leaf">
                  <div class="cell" v-if="listIsNull()">{{ connection.list.deviceType }}</div>
                </td>
              </tr>
              <tr>
                <td class="el-table__cell is-leaf">
                  <div class="cell">设备序列号</div>
                </td>
                <td class="el-table__cell is-leaf">
                  <div class="cell" v-if="listIsNull()">SN:{{ connection.list.deviceSN }}</div>
                </td>
              </tr>
              <tr>
                <td class="el-table__cell is-leaf">
                  <div class="cell">CPU类型</div>
                </td>
                <td class="el-table__cell is-leaf">
                  <div class="cell" v-if="listIsNull()">{{ connection.list.cpuType }}</div>
                </td>
              </tr>
              <tr>
                <td class="el-table__cell is-leaf">
                  <div class="cell">CPU时钟频率</div>
                </td>
                <td class="el-table__cell is-leaf">
                  <div class="cell" v-if="listIsNull()">{{ connection.list.cpuRate }}MHz</div>
                </td>
              </tr>
              </tbody>
            </table>
          </div>
        </el-card>
      </el-col>

      <el-col :span="12" class="card-box">
        <el-card>
          <template #header>
            <Tickets style="width: 1em; height: 1em; vertical-align: middle;"/>
            <span style="vertical-align: middle;">性能监控</span></template>
          <div class="el-table el-table--enable-row-hover el-table--medium">
            <table cellspacing="0" style="width: 100%;">
              <thead>
              <tr>
                <th class="el-table__cell is-leaf">
                  <div class="cell">属性</div>
                </th>
                <th class="el-table__cell is-leaf">
                  <div class="cell">值</div>
                </th>
              </tr>
              </thead>
              <tbody>
              <tr>
                <td class="el-table__cell is-leaf">
                  <div class="cell">CPU占用率</div>
                </td>
                <td class="el-table__cell is-leaf">
                  <div class="cell" v-if="listIsNull()">{{ connection.list.cpuuse }}%</div>
                </td>
              </tr>
              <tr>
                <td class="el-table__cell is-leaf">
                  <div class="cell">内存大小</div>
                </td>
                <td class="el-table__cell is-leaf">
                  <div class="cell" v-if="listIsNull()">{{ kbToGb(connection.list.memmorysize) }}G</div>
                </td>
              </tr>
              <tr>
                <td class="el-table__cell is-leaf">
                  <div class="cell">已使用内存大小</div>
                </td>
                <td class="el-table__cell is-leaf">
                  <div class="cell" v-if="listIsNull()">{{ kbToGb(connection.list.memmoryuse) }}G</div>
                </td>
              </tr>
              <tr>
                <td class="el-table__cell is-leaf">
                  <div class="cell"></div>
                </td>
                <td class="el-table__cell is-leaf" style="height: 39px;">
                
                </td>
              </tr>
              </tbody>
            </table>
          </div>
        </el-card>
      </el-col>

      <el-col :span="24" class="card-box">
        <el-card>
          <template #header>
            <CoffeeCup style="width: 1em; height: 1em; vertical-align: middle;"/>
            <span style="vertical-align: middle;">PCM0链路信息</span></template>
          <div class="el-table el-table--enable-row-hover el-table--medium">
            <table cellspacing="0" style="width: 100%;table-layout:fixed;">
              <tbody>
              <tr>
                <td class="el-table__cell is-leaf">
                  <div class="cell">SS7链路状态</div>
                </td> 
                <td class="el-table__cell is-leaf">
                  <div class="cell" v-if="listIsNull()" 
                  :class="{'text-red': connection.list.ss7link0status === '0'}">{{ getStatus("linkStatus", connection.list.ss7link0status) }}</div>
                </td>
                <td class="el-table__cell is-leaf">
                  <div class="cell">链路值</div>
                </td>
                <td class="el-table__cell is-leaf">
                  <div class="cell" v-if="listIsNull()"
                  :class="{'text-red': connection.list.trunk0status === '0'}">{{ getStatus("linkStatus", connection.list.trunk0status) }}</div>
                </td>
              </tr>
              </tbody>
            </table>
          </div>
        </el-card>
      </el-col>
      <el-col :span="24" class="card-box">
        <el-card>
          <template #header>
            <CoffeeCup style="width: 1em; height: 1em; vertical-align: middle;"/>
            <span style="vertical-align: middle;">PCM2链路信息</span></template>
          <div class="el-table el-table--enable-row-hover el-table--medium">
            <table cellspacing="0" style="width: 100%;table-layout:fixed;">
              <tbody>
              <tr>
                <td class="el-table__cell is-leaf">
                  <div class="cell">SS7链路状态</div>
                </td> 
                <td class="el-table__cell is-leaf">
                  <div class="cell" v-if="listIsNull()" 
                  :class="{'text-red': connection.list.ss7link1status === '0'}">{{ getStatus("linkStatus", connection.list.ss7link1status) }}</div>
                </td>
                <td class="el-table__cell is-leaf">
                  <div class="cell">链路值</div>
                </td>
                <td class="el-table__cell is-leaf">
                  <div class="cell" v-if="listIsNull()"
                  :class="{'text-red': connection.list.trunk1status === '0'}">{{ getStatus("linkStatus", connection.list.trunk1status) }}</div>
                </td>
              </tr>
              </tbody>
            </table>
          </div>
        </el-card>
      </el-col>
      <el-col :span="24" class="card-box">
        <el-card>
          <template #header>
            <CoffeeCup style="width: 1em; height: 1em; vertical-align: middle;"/>
            <span style="vertical-align: middle;">设备锁定状态</span></template>
          <div class="el-table el-table--enable-row-hover el-table--medium">
            <table cellspacing="0" style="width: 100%;table-layout:fixed;">
              <tbody>
              <tr>
                <td class="el-table__cell is-leaf">
                  <div class="cell">设备是否锁定</div>
                </td>
                <td class="el-table__cell is-leaf">
                  <div class="cell" v-if="listIsNull()">{{ getStatus("lockEnable", connection.list.lockEnable) }}</div>
                </td>
                <td class="el-table__cell is-leaf">
                  <div class="cell">锁定状态</div>
                </td>
                <td class="el-table__cell is-leaf">
                  <div class="cell" v-if="listIsNull()">{{ getStatus("lockStatus", connection.list.lockState) }}</div>
                </td>
              </tr>
              </tbody>
            </table>
          </div>
        </el-card>
      </el-col>
      
    </el-row>
  </div>
</template>

<script setup>

onMounted(() => {
  setWebSocket();
});

const statusMappings = {
  linkStatus: {
    "0": "异常",
    "1": "正常",
    "-1": "不存在",
    default: ""
  },
  lockEnable: {
    "0": "未锁定",
    "1": "锁定",
    default: ""
  },
  lockStatus: {
    "0": "否",
    "1": "是",
    default: ""
  }
};

function getStatus(type, status) {
  return statusMappings[type][status] ?? statusMappings[type].default;
}

function kbToGb(kb) {
  let gb = kb / (1024 * 1024)
  return gb.toFixed(2);
}

const IP = import.meta.env.VITE_SERVER_IP;
const PORT = import.meta.env.VITE_SERVER_PORT

let url = `${IP}:${PORT}`

function listIsNull() {
  return connection.list != null ? true : false;
}
 
let connection = reactive({
  list: null
});

function setWebSocket() {
  // 构建 WebSocket 连接的 URL
  let wsUrl = `ws://${url}/ws/equipment-monitoring`;

  // 创建 WebSocket 对象
  let websocket = new WebSocket(wsUrl);

  // 当连接建立时触发
  websocket.onopen = () => {
    // console.log("WebSocket connection established");
  };

  // 当接收到服务端发送的数据时触发
  websocket.onmessage = (event) => {
    // console.log(event.data);
    connection.list = [];
    connection.list = JSON.parse(event.data);
  };

  // 当连接关闭时触发
  websocket.onclose = () => {
    // console.log("WebSocket connection closed");
  };
}
</script>

<style>
.text-red {
  color: rgb(214, 9, 9);
}
</style>