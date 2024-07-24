package com.example.springbootrabbitmq.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 模糊订阅
 *
 * @Author Zhb
 * @create 2024/7/24 上午10:20
 */
@Configuration
public class TopicRabbitConfigVague {

    final static String messages = "/2001/*/up";

    // 新建队列，名字为 topic.messages，默认情况下，如果不手动绑定bind，则直接用该名字就可以做路由键
    // TODO:但是接收端必须和队列名相同！！
    @Bean
    public Queue queueMessages() {
        return new Queue(TopicRabbitConfigVague.messages);
    }

    // 定义 交换机
    @Bean
    TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }


    // TODO:topic方式特别灵活，发送端和接收端都是固定的，但是使用topic方式，可以让发送端发送的数据被不同的接收端(不同路由键)接收到


//    // TODO:交换机绑定(改造)队列和路由关键字：队列queueMessage，路由关键字topic.message
//    // 定义 绑定 队列1 交换机 路由关键字x
//    @Bean
//    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
//        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
//    }

    // TODO:交换机绑定(改造)队列和路由关键字：队列queueMessages，路由关键字/2001/*/up
    // 定义 绑定 队列2 交换机 路由关键字xx
    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("/2001/*/up");
    }

}
