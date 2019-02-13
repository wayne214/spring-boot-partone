# 学习后台开发框架SpringBoot
## Spring boot, Spring mvc, Spring data jpa
## 开发工具
- Java开发神器-IntelliJ IDEA
- PostmanApi接口测试，模拟网络请求
- MySQL数据库
- Maven


### 2019-02-11
- 使用 Spring Boot 和 Thymeleaf上传文件
- FastDFS轻量级分布式文件系统
  - FastDFS特性
    - 文件不分块存储，上传的文件和 OS 文件系统中的文件一一对应
    - 支持相同内容的文件只保存一份，节约磁盘空间
    - 下载文件支持 HTTP 协议，可以使用内置 Web Server，也可以和其他 Web Server 配合使用
    - 支持在线扩容
    - 支持主从文件
    - 存储服务器上可以保存文件属性（meta-data）V2.0 网络通信采用 libevent，支持大并发访问，整体性能更好

### 2019-01-29
- Filter过滤器
- JSR规范数据校验，Hibernate Validator提供了JSR规范中所有内置
约束注解
- ####Thymeleaf模板引擎
  - 字符串拼接
  - If/Unless判断
  - For循环
  - URL
  - 三目运算
  - Switch选择
  - 高阶用法
    - 内联[[]]
    - 基本对象:
      - ctx：上下文对象
      - vars：上下文变量
      - locale：区域对象
      - request：（仅 Web 环境可用）HttpServletRequest 对象
      - response：（仅 Web 环境可用）HttpServletResponse 对象
      - session：（仅 Web 环境可用）HttpSession 对象
      - servletContext：（仅 Web 环境可用）ServletContext 对象
- ####Thymeleaf页面布局
  - Thymeleaf 3.0 推荐使用 th:insert 替换 2.0 的 th:replace。

### 2019-01-28
- 热部署devtools
- test单元测试

### 2019-01-25
- Swagger2自动生成Api文档，可视化
描述和测试API接口,自动生成API接口文档。
- Webjars的使用

### 2019-01-17
- 搭建spring开发环境，使用IntelliJ IDEA构建spring boot项目
- 创建spring boot项目，say Hello world
- spring boot支持jsp开发
- 自由更换banner文件




