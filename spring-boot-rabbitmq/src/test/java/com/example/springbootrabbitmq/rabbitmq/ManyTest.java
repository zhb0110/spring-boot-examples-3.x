package com.example.springbootrabbitmq.rabbitmq;

import com.example.springbootrabbitmq.rabbit.many.NeoSender1;
import com.example.springbootrabbitmq.rabbit.many.NeoSender2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManyTest {

    @Autowired
    private NeoSender1 neoSender1;

    @Autowired
    private NeoSender2 neoSender2;

    /**
     * TODO:一对多：一个发送者，N个接受者,经过测试会均匀的将消息发送到N个接收者中
     *
     * @throws Exception
     */
    @Test
    public void oneToMany() throws Exception {
        for (int i = 0; i < 100; i++) {
            neoSender1.send(i);
        }
    }

    /**
     * TODO:多对多 和一对多一样，接收端仍然会均匀接收到消息
     *
     * @throws Exception
     */
    @Test
    public void manyToMany() throws Exception {
        for (int i = 0; i < 100; i++) {
            neoSender1.send(i);
            neoSender2.send(i);
        }
    }

}
