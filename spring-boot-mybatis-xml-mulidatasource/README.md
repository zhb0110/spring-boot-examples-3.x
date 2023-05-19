## 多数据源，每个配置都单独读取，并手动连接，之后给程序使用

## 初始化启动

创建库spring-boot-mybatis，运行users.sql，配置数据库连接，启动
创建第二个库spring-boot-mybatis-mulidatasource，运行users.sql

## 技术特点

1. 每个手动配置@Mapper
2. 注意引入的mysql驱动版本，如果是6以下，则com.mysql.jdbc.Driver，6以上则com.mysql.cj.jdbc.Driver
3. 可以先@Before获得mvc上下文对象，模拟访问controller牛！！！
4. 启动时其实没有配置spring.datasource，也没有数据库连接，这些都是手动配置不同的配置文件，并一步步实现数据库的，真严谨！！！
   @Configuration @MapperScan @Primary @ConfigurationProperties 实际使用！！