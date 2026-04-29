# LDBC FinBench Transaction Benchmark 测试工程

> 本项目是用于执行 LDBC FinBench 事务型工作负载（Transaction Workload）的图数据库基准测试工程。目前已支持并打通了 GalaxyBase 等图数据库的测试流程。

## 📁 目录结构

本项目包含三个核心模块：

* **`graphybench_driver/`**: 压测驱动核心框架。负责读取参数文件、控制并发线程、发送查询请求并统计吞吐量（Throughput）和延迟（Latency）。
* **`graphybench_transaction_impls/`**: 数据库的具体实现层。包含对接galaxybase图数据库的代码实现和启动脚本。
* **`paper-ldbc-finbench-transaction/`**: 包含配置文件和启动脚本。

## 🛠️ 环境依赖

* **Java**: JDK 1.8 或以上
* **Maven**: 用于项目编译打包
* **GalaxyBase**: 已安装并启动图数据库服务，且已导入 SF10 数据。

## 🚀 快速开始

### 1. 编译项目

在项目根目录下执行编译命令。如果你只需要编译 GalaxyBase 的实现模块，可以按需指定：

```bash
# 仅编译 galaxybase-cypher 并自动拉取底层 driver 依赖
mvn clean package -pl galaxybase-cypher -am -DskipTests
2. 配置参数
进入 graphybench_transaction_impls/galaxybase-cypher 目录，修改 sf10_graphybench_benchmark.properties 配置文件：

确保数据库连接信息正确（endpoint, user, password）。

确认参数文件和增量更新文件的路径（parameters_dir, updates_dir）。

3. 运行压测
在 galaxybase-cypher 目录下启动后台测试脚本：

Bash
nohup sh sf10_graphybench_benchmark.sh > run.log 2>&1 &
实时查看吞吐量与进度：

Bash
watch -n 3 tail -n 30 run.log
⚠️ 常见避坑指南 (Troubleshooting)
报 Delay can not be negative 时间戳错误？
在 properties 配置文件中添加/修改： validate_workload=false。

报 Permission denied 日志写入失败？
由于之前可能使用了 sudo，导致目录权限被锁定。请在目录内执行 sudo chown -R 你的用户名:你的用户名 . 夺回权限，或更换日志文件名。

报 This method does not support transactions？
当前版本的驱动可能不支持带有复杂事务的 Write 操作，可以在配置文件中将相关的 Write 操作置为 false，先进行纯 Read 测试。
