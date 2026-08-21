package com.pm.patientservice.kafka;

import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.producer.acks:all}")
    private String acks;

    @Value("${spring.kafka.producer.retries:3}")
    private int retries;

    @Value("${spring.kafka.producer.properties.delivery.timeout.ms:120000}")
    private int deliveryTimeoutMs;

    @Value("${spring.kafka.producer.properties.request.timeout.ms:30000}")
    private int requestTimeoutMs;

    @Value("${spring.kafka.producer.properties.max.block.ms:10000}")
    private int maxBlockMs;

    @Bean
    public ProducerFactory<String, byte[]> producerFactory() {
        Map<String, Object> configProps = new java.util.HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class);
        configProps.put(ProducerConfig.ACKS_CONFIG, acks);
        configProps.put(ProducerConfig.RETRIES_CONFIG, retries);
        configProps.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, deliveryTimeoutMs);
        configProps.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, requestTimeoutMs);
        configProps.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, maxBlockMs);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, byte[]> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
