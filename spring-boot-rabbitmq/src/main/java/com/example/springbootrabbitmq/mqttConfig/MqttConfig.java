package com.example.springbootrabbitmq.mqttConfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * mqtt配置类
 */
@Data
@ConfigurationProperties(prefix = "mqtt.config")
@Component
public class MqttConfig {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 主机url(代理端即 broker)
     */
    private String hostUrl;

    /**
     * 客户机id
     */
    private String clientId;

    /**
     * 发布的主题
     */
    private String pubTopic;

    /**
     * 订阅的主题
     */
    private String subTopic;

    /**
     * 超时时间
     */
    private int completionTimeout;


}
