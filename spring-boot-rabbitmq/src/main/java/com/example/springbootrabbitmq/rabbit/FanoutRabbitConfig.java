package com.example.springbootrabbitmq.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutRabbitConfig {

    // 新建队列，队列名为fanout.A，默认情况下，如果不手动绑定bind，则直接用该名字就可以做路由键
    // TODO:但是接收端必须和队列名相同！！
    @Bean
    public Queue AMessage() {
        return new Queue("fanout.A");
    }

    // 新建队列，队列名为fanout.B，默认情况下，如果不手动绑定bind，则直接用该名字就可以做路由键
    // TODO:但是接收端必须和队列名相同！！
    @Bean
    public Queue BMessage() {
        return new Queue("fanout.B");
    }

    // 新建队列，队列名为fanout.C，默认情况下，如果不手动绑定bind，则直接用该名字就可以做路由键
    // TODO:但是接收端必须和队列名相同！！
    @Bean
    public Queue CMessage() {
        return new Queue("fanout.C");
    }

    // 定义 交换机
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    // TODO:交换机绑定(改造)队列：队列AMessage
    @Bean
    Binding bindingExchangeA(Queue AMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    // TODO:交换机绑定(改造)队列：队列BMessage
    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }

    // TODO:交换机绑定(改造)队列：队列CMessage
    @Bean
    Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }
}
