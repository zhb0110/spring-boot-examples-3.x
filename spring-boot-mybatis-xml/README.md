## 初始化启动

创建库spring-boot-mybatis，运行users.sql，配置数据库连接，启动

## 技术特点

1. 使用@MapperScan("com.example.springbootmybatisannotation.mapper")全局扫描mapper，否则每个都得手动配置@Mapper
2. 注意引入的mysql驱动版本，如果是6以下，则com.mysql.jdbc.Driver，6以上则com.mysql.cj.jdbc.Driver
3. 可以先@Before获得mvc上下文对象，模拟访问controller牛！！！