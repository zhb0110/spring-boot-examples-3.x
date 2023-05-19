package com.example.springbootshiroauthentication.config;

import org.apache.shiro.mgt.SecurityManager;
import com.example.springbootshiroauthentication.shiro.ShiroRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {

    /*
     * TODO:这里一直提示java.lang.SecurityManager已经被移除，其实这里用的是shiro中的org.apache.shiro.mgt.SecurityManager，在上面手动引入即可
     *
     * TODO:类似FilterRegistrationBean中的getFilter，在其中可配置过滤范围，并自动配置指定了setFilter，就是SecurityManager(自动找到的)
     *
     * */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager1) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // TODO:设置securityManager，类似setFilter
        shiroFilterFactoryBean.setSecurityManager(securityManager1);
        // 登录的url
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后跳转的url
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 未授权url
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        // 1. 定义filterChain，静态资源不拦截
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        // 2. druid数据源监控页面不拦截
        filterChainDefinitionMap.put("/druid/**", "anon");
        // 3. 配置退出过滤器，其中具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/", "anon");
        // 4. 除上以外所有url都必须认证通过才可以访问，未通过认证自动访问LoginUrl
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    // TODO:上面指定的securityManager
    @Bean
    public SecurityManager securityManager() {

        System.out.println("看上面shiroFilterFactoryBean是怎么自动找到这里的：securityManager");

        // 配置SecurityManager，并注入shiroRealm
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }

    @Bean
    public SecurityManager securityManager1() {
        System.out.println("看上面shiroFilterFactoryBean是怎么自动找到这里的：securityManager1");

        // 配置SecurityManager，并注入shiroRealm
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }

    // TODO:类似doFilter，只是内部分的更细，分为doGetAuthorizationInfo权限认证和doGetAuthenticationInfo身份验证
    @Bean
    public ShiroRealm shiroRealm() {
        // 配置Realm，需自己实现
        ShiroRealm shiroRealm = new ShiroRealm();
        return shiroRealm;
    }
}
