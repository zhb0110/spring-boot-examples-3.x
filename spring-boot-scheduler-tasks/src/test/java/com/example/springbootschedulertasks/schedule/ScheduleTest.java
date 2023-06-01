package com.example.springbootschedulertasks.schedule;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 非注解使用
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduleDemo {

    /**
     * TODO:延迟1秒执行一个进程任务
     */
    @Test
    @SneakyThrows
    public void schedule() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.schedule(
                new Runnable() {
                    @Override
                    @SneakyThrows
                    public void run() {
                        log.info("run schedule @ {}", LocalDateTime.now());
                    }
                },
                1000,
                TimeUnit.MILLISECONDS);
        // waiting to process(sleep to mock)
        Thread.sleep(10000);

        // stop
        executor.shutdown();
    }

}
