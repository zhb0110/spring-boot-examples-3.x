package com.example.springbootrabbitmq.emsTest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class EmsTest implements ApplicationRunner {

    @Autowired
    private static RabbitTemplate rabbitTemplate;

    @Autowired
    public EmsTest(RabbitTemplate rabbitTemplate) {
        EmsTest.rabbitTemplate = rabbitTemplate;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        rabbitTemplate.setReplyTimeout(10000);

//        for (int i = 0; i < 10; i++) {
//            List<String> phoneNumbers = (List<String>) rabbitTemplate.convertSendAndReceive("smsExchange", "sms", 3831);
//            System.out.println("电话号码：" + phoneNumbers.toString());
//        }
//
//        EmsTest.getxx();
        System.out.println("结束1" );
//        Thread xx= new ThreadX();
//        xx.start();

        System.out.println("结束" );

    }

    public class ThreadX extends Thread {

        @Override
        public void run(){
            EmsTest.getxx();

            System.out.println("结束2" );
        }

    }

    public static void getxx(){
        for (int i = 0; i < 10; i++) {
            List<String> phoneNumbers = (List<String>) rabbitTemplate.convertSendAndReceive("smsExchange", "sms", 3831);
            System.out.println("电话号码2：" + phoneNumbers.toString());
        }
    }
}
