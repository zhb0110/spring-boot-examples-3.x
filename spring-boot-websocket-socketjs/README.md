# spring-boot-websocket-socketjs

## 订阅方式可以深度改造下

// TODO:限制太大，只能是sockjs客户端的才能订阅
registry.addHandler(myStringWebSocketHandler, "/connect").withSockJS();
// TODO:模拟thingsboard，前端打算使用paho.js或者faye-websocket，但是有些只有nodejs版本，有时间测试
// registry.addHandler(myStringWebSocketHandler, "/connect/**").setAllowedOrigins("*");

## 后续

本文还可以继续挖掘，使用普通ws url连接怎么连？