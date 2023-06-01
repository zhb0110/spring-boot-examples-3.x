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
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 非注解使用
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduleTest {

    /**
     * TODO:schedule 延迟执行一个任务
     * 延迟1秒执行一个进程任务
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

    /**
     * TODO:scheduleAtFixedRate 保证总时间段内的任务的执行次数
     * 延迟0.5秒开始执行，每秒执行一次， 10秒后停止。
     * 每秒执行一次，延迟0.5秒执行。
     */
    @SneakyThrows
    public static void scheduleAtFixedRate() {
        AtomicInteger count = new AtomicInteger(0);
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    @SneakyThrows
                    public void run() {
                        if (count.getAndIncrement() == 2) {
                            Thread.sleep(5000); // 执行时间超过执行周期
                        }
                        log.info("run scheduleAtFixedRate @ {}", LocalDateTime.now());
                    }
                },
                500,
                1000, // 每隔多久执行
                TimeUnit.MILLISECONDS);
        // waiting to process(sleep to mock)
        Thread.sleep(10000);

        // stop
        executor.shutdown();
    }


    /**
     * TODO:scheduleWithFixedDelay 保证了任务之间执行的间隔
     * 延迟0.5秒开始执行，每秒执行一次， 10秒后停止。
     * 每秒执行一次，延迟0.5秒执行。
     */
    @Test
    @SneakyThrows
    public static void scheduleWithFixedDelay() {
        AtomicInteger count = new AtomicInteger(0);
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleWithFixedDelay(
                new Runnable() {
                    @Override
                    @SneakyThrows
                    public void run() {
                        if (count.getAndIncrement() == 2) {
                            Thread.sleep(5000); // 执行时间超过执行周期
                        }
                        log.info("run scheduleWithFixedDelay @ {}", LocalDateTime.now());
                    }
                },
                500,
                1000, // 上次执行完成后，延迟多久执行
                TimeUnit.MILLISECONDS);

        // waiting to process(sleep to mock)
        Thread.sleep(10000);

        // stop
        executor.shutdown();
    }

}
