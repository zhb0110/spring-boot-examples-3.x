## spring-boot-filter-interceptor

本项目使用了hsqldb数据库

### Filter 过滤器--启动优先，访问优先，对所有进入容器的请求起作用，且只在springboot项目中内部可以自定义排序

1. @Order(20) 这种排序方式不稳定，推荐用其他注解，启动时也没起效，访问接口也没起效
2. setOrder(20);和上面的@Order(20)不一样，启动时没用，但是访问接口很有用
3. setUrlPatterns(List.of("/api/*")); 如果请求路径没有包含以下的url，则不会触发下面的过滤器

### Interceptor 拦截器，启动不优先，访问也不优先，只需事先声明即可，主要用于Controller请求和静态资源请求

1. 拦截静态资源，但只需要声明，实际执行的时候不会继续触发这里。
2. 启动顺序，它也是在过滤器后面的
3. 访问路径时也是先Filter过滤器，之后再是拦截器Interceptor

### 登录

1. 登录时，使用了session，并且一直在维护该session

