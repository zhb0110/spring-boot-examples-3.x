package com.example.springbootfilter.web;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * api过滤器注册bean
 */
//@Order(20) 这种排序方式不稳定，推荐用其他注解
// 启动时也没起效，访问接口也没起效
@Component
public class ApiFilterRegistrationBean extends FilterRegistrationBean<Filter> {

    @PostConstruct
    public void init() {
        // TODO:和上面的@Order(20)不一样
        // 启动时没用，但是访问接口很有用
        setOrder(20);
        System.out.println("排序等级20，之后执行");

        setFilter(new ApiFilter());
        // TODO:如果请求路径没有包含以下的url，则不会触发下面的过滤器
        setUrlPatterns(List.of("/api/*"));
    }

    class ApiFilter implements Filter {
        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            // Response返回时，自定义增加头部信息
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.setHeader("X-Api-Version", "1.0");
            chain.doFilter(request, response);
        }
    }

}
