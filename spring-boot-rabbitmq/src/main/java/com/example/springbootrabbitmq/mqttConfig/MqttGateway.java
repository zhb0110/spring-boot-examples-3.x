package com.example.springbootrabbitmq.mqttConfig;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * mqtt网关（发布端需要用到）
 *
 * @author WeiWei
 * @date 2022/08/08
 */
@Component
@MessagingGateway(defaultRequestChannel = MqttServer.OUTBOUND_CHANNEL)
public interface MqttGateway {

    /**
     * 发送到mqtt
     *
     * @param payload 有效载荷
     */
    void sendToMqtt(String payload);

    /**
     * 发送到mqtt
     *
     * @param topic   主题
     * @param payload 消息内容
     */
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, String payload);

    /**
     * 发送到mqtt
     *
     * @param topic   主题
     * @param qos     qos
     * @param payload 消息内容
     */
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, String payload);

}
