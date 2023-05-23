package com.example.springbootwebsocketsocketjs.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class MyStringWebSocketHandler extends TextWebSocketHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 和客户端链接成功的时候触发该方法
     *
     * @param session
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("和客户端建立连接");
    }

    /**
     * 和客户端连接失败的时候触发该方法
     *
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        session.close(CloseStatus.SERVER_ERROR);
        log.error("连接异常", exception);
    }

    /**
     * 和客户端断开连接的时候触发该方法
     *
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        log.info("和客户端断开连接");
    }

    /**
     * 和客户端建立连接后，处理客户端发送的请求
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 获取到客户端发送过来的消息
        String receiveMessage = message.getPayload();
        log.info(receiveMessage);
        // 发送消息给客户端
        session.sendMessage(new TextMessage(fakeAi(receiveMessage)));
        // 关闭连接
        // session.close(CloseStatus.NORMAL);
    }

    private static String fakeAi(String input) {
        if (input == null || "".equals(input)) {
            return "你说什么？没听清︎";
        }
        return input.replace('你', '我')
                .replace("吗", "")
                .replace('?', '!')
                .replace('？', '！');
    }
}
