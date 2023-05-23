package com.example.springbootwebsocketsocketjs.configure;

import com.example.springbootwebsocketsocketjs.handler.MyStringWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketServerConfigure implements WebSocketConfigurer {

    @Autowired
    private MyStringWebSocketHandler myStringWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // TODO:限制太大，只能是sockjs客户端的才能订阅
        registry.addHandler(myStringWebSocketHandler, "/connect").withSockJS();
        // TODO:模拟thingsboard，前端打算使用paho.js或者faye-websocket，但是有些只有nodejs版本，有时间测试
//        registry.addHandler(myStringWebSocketHandler, "/connect/**").setAllowedOrigins("*");

    }
}
