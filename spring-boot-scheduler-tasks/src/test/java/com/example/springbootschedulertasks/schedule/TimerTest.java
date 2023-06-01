package com.example.springbootschedulertasks.schedule;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TimerTest {

    /**
     * TODO:# schedule延迟任务
     * 执行定时任务，延迟1秒开始执行。
     */
    @Test
    @SneakyThrows
    public void timer() {
        // start timer
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                log.info("timer-task @{}", LocalDateTime.now());
            }
        }, 1000);

        // waiting to process(sleep to mock)
        Thread.sleep(3000);

        // stop timer
        timer.cancel();
    }

    /**
     * TODO:# schedule周期任务---保证了任务之间执行的间隔
     * 延迟0.5秒开始执行，每秒执行一次， 10秒后停止。
     * 10行
     */
    @Test
    @SneakyThrows
    public void timerPeriod() {
        // start timer
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @SneakyThrows
            public void run() {
                log.info("timer-period-task @{}", LocalDateTime.now());
                Thread.sleep(100); // 可以设置的执行时间, 来测试当执行时间大于执行周期时任务执行的变化
            }
        }, 500, 1000);

        // waiting to process(sleep to mock)
        Thread.sleep(10000);

        // stop timer
        timer.cancel();
    }

    /**
     * TODO:# scheduleAtFixedRate--保证了总时间段内的执行次数
     * 延迟0.5秒开始执行，每秒执行一次， 10秒后停止。
     * 同时测试某次任务执行时间大于周期时间的变化。
     * 10行
     */
    @Test
    @SneakyThrows
    public void timerFixedRate() {
        // start timer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int count = 0;

            @SneakyThrows
            public void run() {
                if (count++ == 2) {
                    Thread.sleep(5000); // 某一次执行时间超过了period(执行周期）
                }
                log.info("timer-fixedRate-task @{}", LocalDateTime.now());

            }
        }, 500, 1000);

        // waiting to process(sleep to mock)
        Thread.sleep(10000);

        // stop timer
        timer.cancel();
    }
}
