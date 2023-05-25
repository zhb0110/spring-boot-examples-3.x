package com.example.springbootrabbitmq.rabbit.many;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NeoSender2 {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
     * @param i 从@Test那里接收到的参数
     */
    public void send(int i) {
        String context = "spirng boot neo queue" + " ****** " + i;
        System.out.println("Sender2 : " + context);
        this.rabbitTemplate.convertAndSend("neo", context);
    }
}
