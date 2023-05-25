package com.example.springbootrabbitmq.rabbitmq;

import com.example.springbootrabbitmq.rabbit.topic.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicTest {

    @Autowired
    private TopicSender sender;

    // 只有一个接收
    @Test
    public void topic() throws Exception {
        sender.send();
    }


    // 两个端接收
    @Test
    public void topic1() throws Exception {
        sender.send1();
    }

    // 一个端接收
    @Test
    public void topic2() throws Exception {
        sender.send2();
    }


}
