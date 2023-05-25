package com.example.springbootrabbitmq.rabbitmq;

import com.example.springbootrabbitmq.rabbit.hello.HelloSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqHelloTest {

    @Autowired
    private HelloSender helloSender;

    /**
     * 一对一
     *
     * @throws Exception
     */
    @Test
    public void hello() throws Exception {
        helloSender.send();
    }

}
