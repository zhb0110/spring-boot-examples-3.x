package com.example.springbootschedulequartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * TODO:定义Job，需要继承QuartzJobBean，并重载executeInternal方法即可定义你自己的Job执行逻辑
 */
@Slf4j
public class HelloJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        // get parameters 获得参数
        context.getJobDetail().getJobDataMap().forEach(
                (k, v) -> log.info("param, key:{}, value:{}", k, v) // TODO:接受传参
        );
        // your logics 你的执行时间
        log.info("Hello Job执行时间: " + new Date());
    }
}
