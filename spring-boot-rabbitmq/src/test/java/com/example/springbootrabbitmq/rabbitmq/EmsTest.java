package com.example.springbootrabbitmq.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmsTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;



    @Test
    public void test() throws Exception {
        rabbitTemplate.setReplyTimeout(10000);
        List<String> phoneNumbers = (List<String>) rabbitTemplate.convertSendAndReceive("smsExchange", "sms", 3831);
        System.out.println("电话号码：" + phoneNumbers.toString());
    }

}
