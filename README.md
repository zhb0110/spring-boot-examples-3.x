# spring-boot-examples-3.x

* SpringBoot实践，简单样例。
* Java 17 + SpringBoot 3.x + Spring 6.x + Maven.

## Spring Boot 集成

1. spring-boot-command-line-runner:项目启动时初始化资源案例
2. **spring-boot-filter**:使用过滤器Filter和拦截器Interceptor(仅controller)
3. **spring-boot-scheduler**:定时任务scheduler示例
4. spring-boot-thymeleaf:thymeleaf语法、布局使用示例
5. spring-boot-web-thymeleaf:thymeleaf增删改查示例--
6. spring-boot-package:单元测试、集成测试、打jar/war包、定制启动参数使用案例
7. spring-boot-file-upload:上传文件使用案例
8. spring-boot-jpa-thymeleaf-curd:jpa thymeleaf列表、增删改查使用案例
9. spring-boot-mail:邮件发送使用示例
10. spring-boot-mongodb:mongodb增删改查示例 多数据源使用案例
11. spring-boot-memcached-spymemcached:集成memcached使用案例
12. **spring-boot-aop-log**:AOP记录用户操作日志
13. spring-boot-jackson:
14. spring-boot-testing:编写单元测试
15. spring-boot-actuator:监控应用
16. spring-boot-admin:admin监控服务
17. spring-boot-devtools:devtools开发工具，并且能热部署
18. spring-boot-exception:异常处理
19. spring-boot-eureka-server-discovery:eureka服务治理-
20. spring-boot-hystrix:Hystrix服务--
21. spring-boot-feign:feign声明式服务调用--
22. spring-boot-security:启用spring security，并配置--可用shiro替代
23. spring-boot-webflux:

## Spring Boot 集成 消息中间件

1. **spring-boot-rabbitmq**:rabbitmq各种常见场景使用示例
2. **spring-boot-kafka**:整合kafka示例
3. **spring-boot-websocket-socketjs**:整合websocket
4. spring-boot-rocketmq:
5. spring-boot-activemq:artemis
6. spring-boot-zookeeper:

## Spring Boot 集成 缓存中间件

1. **spring-boot-redis**:redis 缓存示例
2. spring-boot-ehcache:使用Ehcache缓存数据

## Spring Boot 集成 数据中间件

1. **spring-boot-mybatis**:mybatis注解、xml使用、增删改查、多数据源使用示例
2. **spring-boot-jpa**:jpa操作，增删改查多数据源使用示例
3. spring-boot-mapper-pagehelper:整合mybatis通用mapper和pagehelper--
4. spring-boot-elasticsearch:全文检索引擎
5. spring-boot-jdbctemplate:

## Spring Boot 集成 文档插件

1. spring-boot-openapi:
2. spring-boot-swagger2:

## Spring Boot 集成 Shiro

1. **spring-boot-shiro**:
2. **spring-boot-shiro-jwt**:shiro整合JWT

## Spring Boot 集成 Docker

1. spring-boot-docker:docker使用案例
2. spring-boot-dockercompose-mysql-nginx:docker compose+nginx+mysql使用案例

## Spring Cloud

1. **spring-cloud-alibaba**:阿里巴巴注册，配置，控制

## Java

1. **多线程**：看廖雪峰文档
2. Java IO:
3. java http:
4. java tcp:
5. java udp:

## Spring

1. spring-javaMail:
2. spring-jms:
3. spring-scheduler:
4. spring-jmx:

## Spring Boot 综合

1. demo:快速入门-加了Spring Web:使用 Spring MVC 构建 Web，包括 RESTful，应用程序。使用 Apache Tomcat 作为默认的嵌入式容器。
2. spring-boot:尝试使用 Spring Boot 构建应用程序-包括单元测试@Test，生产级服务(spring-boot-starter-actuator)
   ，打包，关键是启动器和加载bean
3. rest-service:尝试构建一个 RESTful Web 服务。JSON 表示形式进行响应(默认使用jackson)，创建资源类(实体)、控制器类，并打包jar
4. accessing-data-jpa:JPA+H2。使用JPA访问数据，简单实体(@Entity,@Id/@GeneratedValue(strategy=GenerationType.AUTO))
   ，简单查询类(CrudRepository)，应用程序类(@Bean，CommandLineRunner，org.slf4j.LoggerFactory)
   ，启动时启动器-加载bean，直接在命令行执行并日志输出H2库中的操作数据
5. springRedis:基础Redis。加了Spring Data Redis和Lombok，配置了application.yml，就做了测试@Test
6. springDataReactiveRedis:使用 Redis 以反应方式访问数据。应用程序使用 Spring Data Redis 和 Project Reactor 与 Redis
   数据存储进行交互，在不阻塞的情况下存储和检索对象。Spring Reactive Web，Spring Data Reactive
   Redis和Lombok。注意如果改动了redis密码，需要在application中加redis的密码
7. messaging-redis:使用 Redis 发送消息。使用 Spring Data Redis 发布和订阅使用 Redis 发送的消息的过程，比较奇葩的需求
8. securing-web:使用受 Spring security保护的资源创建简单 Web 应用程序的过程。构建一个Spring
   MVC应用程序，该应用程序使用由固定用户列表支持的登录表单来保护页面。“依赖项”：“Spring
   Web”和“Thymeleaf”，实用型比较强，加入了spring-boot-starter-security和spring-security-test，注意模板引擎的模板文件(
   类似JSP)都是放在resources/templates下
9. accessing-data-mysql:JPA+Mysql。'@Controller,@Repository(Dao)，@Entity，挺实用的，三种依赖Spring Web，Spring Data
   JPA和MySQL驱动程序。spring.jpa.hibernate.ddl-auto生产环境为none，开发环境为update，数据库为：db_example
10. mybatis-sample:Mybatis+H2。'不需要@Entity，@Repository(Dao)->@Mapper，需要@Controller，实用，两种依赖MyBatis Framework，H2
    Database；演示springboot启动项目执行sql逻辑，schema.sql/data.sql；@Test执行：测试执行能看到执行的文件不一样，其他都和正常启动差不多
11. mybatis-mysql:Mybatis+Mysql。'不需要@Entity，@Repository(Dao)->@Mapper，需要@Controller，实用，两种依赖MyBatis
    Framework，Mysql驱动；演示springboot启动项目执行sql逻辑，schema.sql/data.sql；@Test执行：测试执行能看到执行的文件不一样，其他都和正常启动差不多；难点在mysql不是内存数据库，无法默认初始化执行sql，springboot2.7+版本需配置spring.sql.init.mode=always



