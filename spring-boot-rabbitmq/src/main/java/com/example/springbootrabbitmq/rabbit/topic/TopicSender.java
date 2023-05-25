package com.example.springbootrabbitmq.rabbit.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
     * 只有topic.#能接收到，即只有Topic Receiver2能接收到
     */
    public void send() {
        String context = "hi, i am message all";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.1", context);
    }

    /**
     * topic.#和topic.message 能接收到，即Topic Receiver1和Topic Receiver2都能接收
     * TODO:重点：topic方式符合多个接收端条件，则-->就是说可以实现多个地点重复接收一个消息！！！
     */
    public void send1() {
        String context = "hi, i am message 1";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.message", context);
    }

    /**
     * 只有topic.#能接收到，即只有Topic Receiver2能接收到
     */
    public void send2() {
        String context = "hi, i am messages 2";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.messages", context);
    }
}
