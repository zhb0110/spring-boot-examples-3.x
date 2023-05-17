package com.example.springbootfilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SpringBootFilterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootFilterApplication.class, args);
    }

    // -- Spring Mvc configuration
    // 拦截器Interceptor，仅拦截Controller ---------------------------------------------------
    @Bean
    WebMvcConfigurer createWebMvcConfigurer(@Autowired HandlerInterceptor[] interceptors) {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                System.out.println("测试顺序 registry = " + registry);

                // TODO:拦截静态资源，但只需要声明，实际执行的时候不会继续触发这里。
                // TODO:启动顺序，它也是在过滤器后面的
                // TODO:访问路径时也是先Filter过滤器，之后再是拦截器Interceptor
                // 请求路径，后面是资源在服务器地址
                registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
                registry.addResourceHandler("/static1/**").addResourceLocations("classpath:/static/");
            }
        };
    }
}
