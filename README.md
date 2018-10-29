### 项目简介 
- [x] 代码拥有详细注释 无复杂逻辑 核心使用 SpringBoot 2.0.5.RELEASE
- [x] JPA + Mybatis-Plus任意切换
- [x] 操作日志记录方式任意切换Mysql或Elasticseach记录
- [x] 极简代码生成 只需输入类名和字段 自动创建数据库表
- [x] 支持社交账号、短信等多方式登录 不干涉原用户数据 实现第三方账号管理
- [x] 基于Websocket消息推送管理、基于Quartz定时任务管理
- [x] Actuator可视化数据监控
- [x] 后台提供分布式限流、同步锁、验证码等工具类 前端提供空白Vue模版
- [x] 为什么要前后端分离
    - 都什么时代了还在用JQuery？ 

### 前端所用技术
- Vue 2.5.x、Vue Cli 3.x、iView、iview-admin、iview-area、Vuex、Vue Router、ES6、webpack、axios、echarts、cookie等

### 后端所用技术
##### 各框架依赖版本皆使用目前最新版本
- Spring Boot 2.0.5.RELEASE
- SpringMVC
- Spring Security
- [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/2.0.6.RELEASE/reference/html/)
- [MyBatis-Plus](http://mp.baomidou.com)
- [Redis](https://github.com/Exrick/xmall/blob/master/study/Redis.md)
- [Elasticsearch](https://github.com/Exrick/xmall/blob/master/study/Elasticsearch.md)：基于Lucene分布式搜索引擎
- [Druid](http://druid.io/)：阿里高性能数据库连接池 [Druid配置官方中文文档](https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter)
- [Json Web Token(JWT)](https://jwt.io/)
- [Quartz](http://www.quartz-scheduler.org)：定时任务
- [Beetl](http://ibeetl.com/guide/#beetl)：模版引擎 代码生成使用
- [Hutool](http://hutool.mydoc.io/)：Java工具包
- [Jasypt](https://github.com/ulisesbocchio/jasypt-spring-boot)：配置文件加密(thymeleaf作者开发)
- [Swagger2](https://github.com/Exrick/xmall/blob/master/study/Swagger2.md)：Api文档生成
- MySQL
- [Nginx](https://github.com/Exrick/xmall/blob/master/study/Nginx.md)
- [Maven](https://github.com/Exrick/xmall/blob/master/study/Maven.md)
- 第三方SDK或服务
    - [阿里云对象存储 OSS](https://help.aliyun.com/document_detail/32008.html?spm=a2c4g.11174283.6.700.28c87da2neZQQZ)
    - [七牛云文件存储服务](https://developer.qiniu.com/kodo/sdk/1239/java)
    - [Mob全国天气预报接口](http://api.mob.com/#/apiwiki/weather)：需注册账号创建应用后申请填入AppKey后免费使用
    - [Vaptcha人机验证码](https://www.vaptcha.com/)
    - [阿里云短信服务](https://dysms.console.aliyun.com)
- 其它开发工具
    - [Lombok](https://projectlombok.org/)
    - ~~[JRebel](https://github.com/Exrick/xmall/blob/master/study/JRebel.md)：开发热部署~~ 已无法免费使用 改回devtools
    - [阿里JAVA开发规约插件](https://github.com/alibaba/p3c)