package com.example.springbootschedulequartz.config;

import com.example.springbootschedulequartz.job.HelloJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    /**
     * TODO:触发器：一个调度参数的配置，什么时候去调
     *
     * @return
     */
    @Bean
    public Trigger printTimeJobTrigger() {
        // 每秒执行一次
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/1 * * * * ?");
        // TODO:注意这里调用了具体执行 helloJobDetail()
        return TriggerBuilder.newTrigger()
                .forJob(helloJobDetail())
                .withIdentity("quartzTaskService")
                .withSchedule(cronScheduleBuilder)
                .build();
    }

    /**
     * TODO:具体执行，一个具体的可执行的调度程序，Job 是这个可执行程调度程序所要执行的内容，另外 JobDetail 还包含了这个任务调度的方案和策略。
     *
     * @return
     */
    @Bean("helloJob")
    public JobDetail helloJobDetail() {
        // TODO:注意这里调用了任务：HelloJob
        return JobBuilder.newJob(HelloJob.class)
                .withIdentity("DateTimeJob") // TODO:定义任务名称，如果后面再加一个参数，则是分组参数group
                .usingJobData("msg", "Hello Quartz")// TODO:传参
                .storeDurably()// TODO:即使没有Trigger关联时，也不需要删除该JobDetail
                .build();
    }
}
