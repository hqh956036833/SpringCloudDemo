package com.test.kafka.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface KafkaProducerService {

    Map<String, Object> sendMessage(String topic,Map<String, Object> message);
}
