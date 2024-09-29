package com.test.order.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient("kafka")
public interface KafkaClient {
    @PostMapping("/kafka/sendMsg")
    Map<String, Object> sendMessage(@RequestParam("topic") String topic, @RequestBody Map<String, Object> message);
}
