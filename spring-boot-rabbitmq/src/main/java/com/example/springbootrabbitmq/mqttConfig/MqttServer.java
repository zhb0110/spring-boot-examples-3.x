package com.example.springbootrabbitmq.mqttConfig;

import com.alibaba.fastjson.JSONObject;
import com.example.iotx.config.RabbitProduct;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Objects;

/**
 * mqtt服务类
 * 一种基于发布/订阅（publish/subscribe）模式的轻量级通讯协议，通过订阅相应的主题来获取消息，
 * 是物联网（Internet of Thing）中的一个标准传输协议
 * ClientId是MQTT客户端的标识。MQTT服务端用该标识来识别客户端。因此ClientId必须是独立的。
 * clientID需为全局唯一。如果不同的设备使用相同的clientID同时连接物联网平台，那么先连接的那个设备会被强制断开。
 *
 * @author WeiWei
 * @date 2022/08/08
 */
@Slf4j
@Configuration
public class MqttServer {

    @Autowired
    private RabbitProduct rabbitProduct;

    /**
     * 出站通道
     */
    public static final String OUTBOUND_CHANNEL = "mqttOutboundChannel";

    /**
     * 输入通道
     */
    public static final String INPUT_CHANNEL = "mqttInputChannel";

    /**
     * mqtt配置
     */
    @Autowired
    private MqttConfig mqttConfig;


    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        log.info(mqttConfig.toString());
    }

    /**
     * mqtt客户工厂
     *
     * @return {@link MqttPahoClientFactory}
     */
    @Bean
    public MqttPahoClientFactory clientFactory() {

        MqttConnectOptions options = new MqttConnectOptions();
        //配置MqttConnectOptions

        // 同时进行发布的最大值，防止出现发布太多的问题
        options.setMaxInflight(100000);

        options.setServerURIs(new String[]{mqttConfig.getHostUrl()});
        options.setUserName(mqttConfig.getUsername());
        options.setPassword(mqttConfig.getPassword().toCharArray());

        // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，
        // 把配置里的 cleanSession 设为false，客户端掉线后 服务器端不会清除session，
        // TODO:当重连后可以接收之前订阅主题的消息。当客户端上线后会接受到它离线的这段时间的消息
        options.setCleanSession(false);
//        options.setServerURIs(hostUrl.split(","));
        // TODO:设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
        options.setKeepAliveInterval(20);

        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(options);
        return factory;
    }

    /**
     * mqtt出站通道
     *
     * @return {@link MessageChannel}
     */
//    @Bean(value = OUTBOUND_CHANNEL)
    @Bean(value = OUTBOUND_CHANNEL)
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    /**
     * mqtt出站handler
     * 对消费者端点调用
     *
     * @return {@link MessageHandler}
     */
    @Bean
    @ServiceActivator(inputChannel = OUTBOUND_CHANNEL)
    public MessageHandler mqttOutboundHandler() {
        //MqttPahoMessageHandler初始化
        MqttPahoMessageHandler handler = new MqttPahoMessageHandler(mqttConfig.getClientId() + "_send_handler_", clientFactory());
        //设置默认的qos级别--TODO:可能会重复发，需要去重
        handler.setDefaultQos(1);
        //保留标志的默认值。如果没有mqtt_retained找到标题，则使用它。如果提供了自定义，则不使用它converter。这里不启用
        handler.setDefaultRetained(false);
        //设置发布的主题
        handler.setDefaultTopic(mqttConfig.getPubTopic());
        //当 时true，调用者不会阻塞。相反，它在发送消息时等待传递确认。默认值为false（在确认交付之前发送阻止）。
        handler.setAsync(false);
        //当 async 和 async-events 都为 true 时，会发出 MqttMessageSentEvent（请参阅事件）。它包含消息、主题、客户端库生成的messageId、clientId和clientInstance（每次连接客户端时递增）。当客户端库确认交付时，会发出 MqttMessageDeliveredEvent。它包含 messageId、clientId 和 clientInstance，使传递与发送相关联。任何 ApplicationListener 或事件入站通道适配器都可以接收这些事件。请注意，有可能在 MqttMessageSentEvent 之前接收到 MqttMessageDeliveredEvent。默认值为false。
        handler.setAsyncEvents(false);
        return handler;
    }

    /**
     * mqtt输入通道
     *
     * @return {@link MessageChannel}
     */
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    /**
     * 入站
     *
     * @return {@link MessageProducer}
     */
    @Bean
    public MessageProducer inbound() {
        //配置订阅端MqttPahoMessageDrivenChannelAdapter
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(
                        mqttConfig.getClientId() + "_receive_inbound_", clientFactory(), mqttConfig.getSubTopic().split(","));
        // 设置超时时间
        adapter.setCompletionTimeout(mqttConfig.getCompletionTimeout());
        //设置默认的消息转换类
        adapter.setConverter(new DefaultPahoMessageConverter());
        //设置qos级别
        adapter.setQos(1);
        //设置入站管道
        adapter.setOutputChannel(mqttInputChannel());
        adapter.setTaskScheduler(new ConcurrentTaskScheduler());
        return adapter;
    }

    /**
     * 消息处理程序
     *
     * @return {@link MessageHandler}
     */
    @Bean
    @ServiceActivator(inputChannel = INPUT_CHANNEL)
    public MessageHandler messageHandler() {
        return message -> {
            // mqtt_receivedTopic
            String topic = Objects.requireNonNull(message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC)).toString();
            log.info("订阅主题为: {}", topic);
            String[] topics = mqttConfig.getSubTopic().split(",");
            for (String t : topics) {
                if (t.equals(topic)) {


                    if ("topic/devices/upload".equals(t)) {
                        // 如果是topic/devices/upload，设备上传
                        // 放入队列生产者
//                        try {
                        JSONObject payload = JSONObject.parseObject(String.valueOf(message.getPayload()));
//                            String s_utf81 = new String(message.getPayload(), "UTF-8");
//                            String s_utf8 = new String((byte[]) message.getPayload(), "UTF-8");
//                            JSONObject payload = (JSONObject) message.getPayload();
                        rabbitProduct.sendDeviceMSG(payload);

                        log.info("接收到该主题消息为: {}", payload);
//                        } catch (UnsupportedEncodingException e) {
//                            throw new RuntimeException(e);
//                        }


                    } else if ("topic/devices/rpc/reply/+".equals(t)) {
                        // TODO:略
                        //  如果是，设备反馈
                        // 放入队列生产者--可以用另一个方法
//                        rabbitProduct.sendMSG(new Msg("设备1", "{\"height\":\"100\",\"width\":\"200\"}", "{\"state\":\"1\"}"));
                    }


                    log.info("接收到该主题消息为: {}", message.getPayload());
                }
            }
        };
    }

//    public static void main(String[] args) {
//        DeviceMessage deviceMessage = new DeviceMessage();
//        deviceMessage.setDeviceName("设备1");
//        JSONObject attributes = new JSONObject();
//        attributes.put("height", "100");
//        attributes.put("width", "200");
//        deviceMessage.setAttributes(attributes);
//        JSONObject telemetry = new JSONObject();
//        telemetry.put("state", "1");
//        deviceMessage.setTelemetry(telemetry);
//
//
//        System.out.println("ccc");
//
//        log.info("接收到该主题消息为: {}", deviceMessage);
//        log.info("接收到该主题消息为: {}", deviceMessage.toString());
//
//    }

}