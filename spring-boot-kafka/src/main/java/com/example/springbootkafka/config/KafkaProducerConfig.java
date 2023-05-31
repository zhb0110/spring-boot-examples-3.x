package com.example.springbootkafka.config;

import com.example.springbootkafka.domain.Message;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * 生产者配置
 */
@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    /**
     * TODO:通过KafkaTemplate发送消息，固定配置该方法
     *
     * @return
     */
    @Bean
//    public KafkaTemplate<String, String> kafkaTemplate() {
    public KafkaTemplate<String, Message> kafkaTemplate() {
        // 生产者 实现策略
        return new KafkaTemplate<>(producerFactory());
    }

    // TODO:配置了Kafka Producer实例的策略
    @Bean
//    public ProducerFactory<String, String> producerFactory() {
    public ProducerFactory<String, Message> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        // bootstrapServers为Kafka生产者的地址
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        // TODO:key的序列化策略；
        // StringSerializer，只测试发送简单的string类型的消息
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        // TODO:value的序列化策略
        // StringSerializer，只测试发送简单的string类型的消息
//        configProps.put(
//                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
//                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

}
