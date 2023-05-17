package com.example.springbootfilter.web;

import com.example.springbootfilter.entity.User;
import com.example.springbootfilter.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 身份验证过滤器注册bean
 * <p>
 * FilterRegistrationBean 类似过滤器工厂
 */
//@Order(10) 这种排序方式不稳定，推荐用其他注解
// 启动时也没起效，访问接口也没起效
@Component
public class AuthFilterRegistrationBean extends FilterRegistrationBean<Filter> {
    @Autowired
    UserService userService;

    @Override
    public Filter getFilter() {
        // TODO:和上面的@Order(10)不一样
        // 启动时没用，但是访问接口很有用
        setOrder(10);
        System.out.println("过滤器 排序等级10，首先执行，且所有路径访问都执行该过滤");
        return new AuthFilter();
    }

    class AuthFilter implements Filter {

        final Logger logger = LoggerFactory.getLogger(getClass());

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            HttpServletRequest req = (HttpServletRequest) request;
            try {
                authenticateByHeader(req);
            } catch (RuntimeException e) {
                logger.warn("login by authorization header failed.", e);
            }
            // TODO:通过过滤器，回到正常请求去，如果没有这一步，则正常的请求不会执行
            chain.doFilter(request, response);
        }

        //        进行身份验证根据 头部信息
        private void authenticateByHeader(HttpServletRequest req) {
            //
            String authHeader = req.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Basic ")) {
                logger.info("尝试通过授权头进行身份验证 try authenticate by authorization header...");
                String up = new String(Base64.getDecoder().decode(authHeader.substring(6)), StandardCharsets.UTF_8);
                int pos = up.indexOf(':');
                if (pos > 0) {
                    String email = URLDecoder.decode(up.substring(0, pos), StandardCharsets.UTF_8);
                    String password = URLDecoder.decode(up.substring(pos + 1), StandardCharsets.UTF_8);
                    User user = userService.signin(email, password);
                    req.getSession().setAttribute(UserController.KEY_USER, user);
                    logger.info("user {} login by authorization header ok.", email);
                }
            }
        }


    }
}
