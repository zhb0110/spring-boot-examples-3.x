package com.example.springbootkafka.controller;

import com.example.springbootkafka.domain.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发布消息
 */
@RestController
public class SendMessageController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
    private KafkaTemplate<String, Message> kafkaTemplate;

//    @GetMapping("send/{message}")
//    public void send(@PathVariable String message) {
//        // TODO:简单的发送，不关心是否送到
//        // key-value都为String类型
//        this.kafkaTemplate.send("test", message);
//
//        // TODO:由于是异步的，通过监听发送是否发送到---jdk版本太高，该写法废弃，使用com.google.guava也没用上，有时间研究其他方式
////        ListenableFuture<SendResult<String, String>> future =  this.kafkaTemplate.send("test", message);
////        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
////            @Override
////            public void onSuccess(SendResult<String, String> result) {
////                logger.info("成功发送消息：{}，offset=[{}]", message, result.getRecordMetadata().offset());
////            }
////
////            @Override
////            public void onFailure(Throwable ex) {
////                logger.error("消息：{} 发送失败，原因：{}", message, ex.getMessage());
////            }
////        });
//
//    }

    /**
     * 测试4:传递对象
     * TODO:注意使用了topic test2，对象类型，因为就测试使用了topic test，而且是string类型，时间没过期，导致初始化配置config的时候会失败，所以创建了test2，专用对象传值
     *
     * @param message
     */
    @GetMapping("send/{message}")
    public void sendMessage(@PathVariable String message) {
        this.kafkaTemplate.send("test2", new Message("mrbird", message));
    }
}
