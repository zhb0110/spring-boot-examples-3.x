package com.example.springbootkafka.listener;

import com.example.springbootkafka.domain.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageListener {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * TODO:测试1
     * TODO:通过@KafkaListener注解来监听名称为test的Topic，消费者分组的组名为test-consumer
     *
     * @param message
     */
//    @KafkaListener(topics = "test", groupId = "test-consumer")
//    public void listen(String message) {
//        logger.info("接收消息: {}", message);
//    }

    /**
     * TODO:测试2
     * TODO:通过@KafkaListener注解来监听名称为test的Topic，消费者分组的组名为test-consumer
     * 接收分区 partition
     *
     * @param message
     */
//    @KafkaListener(topics = "test", groupId = "test-consumer")
//   TODO:旧版参数：RECEIVED_PARTITION_ID public void listen(String message,@Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
//    public void listen(String message, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
//        logger.info("接收消息: {}，partition：{}", message, partition);
//    }

    /**
     * TODO:测试3
     * TODO:通过@KafkaListener注解来监听名称为test的Topic，消费者分组的组名为test-consumer
     * 接收分区 partition
     * TODO:接收指定分区
     *
     * @param message
     */
//    @KafkaListener(groupId = "test-consumer",
//            topicPartitions = @TopicPartition(topic = "test",
//                    partitionOffsets = {
//                            @PartitionOffset(partition = "0", initialOffset = "0")
//                    }))
//    public void listen(String message,@Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
//    public void listen(String message, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
//        logger.info("接收消息: {}，partition：{}", message, partition);
//    }

    /**
     * 测试4：传递对象
     * TODO:注意使用了topic test2，对象类型，因为就测试使用了topic test，而且是string类型，时间没过期，导致初始化配置config的时候会失败，所以创建了test2，专用对象传值
     *
     * @param message
     * @param partition
     */
    @KafkaListener(groupId = "test-consumer",
            topicPartitions = @TopicPartition(topic = "test2",
                    partitionOffsets = {
                            @PartitionOffset(partition = "0", initialOffset = "0")
                    }))
//    public void listen(String message,@Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
    public void listen(Message message, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
        logger.info("接收消息: {}，partition：{}", message, partition);
    }
}
