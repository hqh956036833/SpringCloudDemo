package com.test.kafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Map;

public interface KafkaConsumerService {
    void messageListener1(ConsumerRecord<?, ?> record);
}
