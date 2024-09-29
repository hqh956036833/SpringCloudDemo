package com.test.kafka.controller;

import com.test.kafka.service.KafkaProducerService;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class KafkaProducerController {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping("kafka/sendMsg")
    @ResponseBody
    public Map<String, Object> sendMessage(@RequestParam String topic, @RequestBody Map<String, Object> message) {
        logger.info("向指定主题发送信息，topic={},message={}", topic, message);
        return kafkaProducerService.sendMessage(topic,message);
    }
}
