package com.example.springbootrabbitmq.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 队列配置：
 * TODO:最简单的方式：Direct Exchange 默认，最简单的模式，根据key全文匹配去寻找队列
 */
@Configuration
public class RabbitConfig {

    /**
     * 新建队列，队列名为hello，默认情况下，如果不手动绑定bind，则直接用该名字就可以做路由键
     * TODO:但是接收端必须和队列名相同！！
     * TODO:简单实用，发送接收在hello包下
     *
     * @return
     */
    @Bean
    public Queue Queue() {
        return new Queue("hello");
    }


    /**
     * 新建队列，队列名为neo，默认情况下，如果不手动绑定bind，则直接用该名字就可以做路由键
     * TODO:但是接收端必须和队列名相同！！
     * TODO:多对多:neo
     *
     * @return
     */
    @Bean
    public Queue neoQueue() {
        return new Queue("neo");
    }


    /**
     * 新建队列，队列名为object，默认情况下，如果不手动绑定bind，则直接用该名字就可以做路由键
     * TODO:高级用法: 对对象支持object
     *
     * @return
     */
    @Bean
    public Queue objectQueue() {
        return new Queue("object");
    }


}
