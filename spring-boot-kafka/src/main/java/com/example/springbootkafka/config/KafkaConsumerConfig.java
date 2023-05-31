package com.example.springbootkafka.config;

import com.example.springbootkafka.domain.Message;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * 消费者配置
 */
@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String consumerGroupId;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;

    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
    public ConcurrentKafkaListenerContainerFactory<String, Message> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory
//                = new ConcurrentKafkaListenerContainerFactory<>();
        ConcurrentKafkaListenerContainerFactory<String, Message> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        // ---过滤器配置---
        // TODO:过滤了一些关键字
        // 接收RecordFilterStrategy<K, V>，他是一个函数式接口
//        factory.setRecordFilterStrategy(
//                new RecordFilterStrategy<String, String>() {
//                    @Override
//                    public boolean filter(ConsumerRecord<String, String> consumerRecord) {
//                        return consumerRecord.value().contains("fuck");
//                    }
//                }
//        );
        // 上面的也可以这么写：lambda表达式写法，只要写接口类中默认方法的参数就可以，这里参数只有一个ConsumerRecord<String, String> consumerRecord
//        factory.setRecordFilterStrategy(
//                consumerRecord -> consumerRecord.value().contains("fuck")
//        );

        return factory;
    }

    @Bean
//    public ConsumerFactory<String, String> consumerFactory() {
    public ConsumerFactory<String, Message> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        // 分组，也可以不分组
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                consumerGroupId);
        // 消息读取策略:当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，消费新产生的该分区下的数据
        props.put(
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
                autoOffsetReset);
//        props.put(
//                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
//                StringDeserializer.class);
//        props.put(
//                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
//                StringDeserializer.class);
//        return new DefaultKafkaConsumerFactory<>(props);

        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                new JsonDeserializer<>(Message.class));
    }
}
