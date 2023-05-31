## spring-boot-kafka

测试配置kafka的能力

## 多个topic对应的数据类型不同，则需重新创建

注意使用了topic test2，对象类型，因为就测试使用了topic test，而且是string类型，时间没过期，导致初始化配置config的时候会失败，所以创建了test2，专用对象传值

## Serializable

如果要用到序列化Serializable对象，要么老老实实写getter/setter，要么加lombok的注解@Data，仅仅是加了maven引入是没用的

## lambda表达式 匿名函数编程

函数式接口的一个匿名实现类，该接口只能有一个抽象方法！！！
jdk1.8开始支持Lambda