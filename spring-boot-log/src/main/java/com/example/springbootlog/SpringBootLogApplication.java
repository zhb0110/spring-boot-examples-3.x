package com.example.springbootlog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootLogApplication {

    private static Logger log = LoggerFactory.getLogger(SpringBootLogApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLogApplication.class, args);


        String msg = "print log, current level: {}";
        log.trace(msg, "trace");
        log.debug(msg, "debug");
        log.info(msg, "info");
        log.warn(msg, "warn");
        log.error(msg, "error");
    }

    @Bean
    public void test() {
        log.info("-------哈哈---------");
    }

}
