package com.test.kafka.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient("storage")
public interface StorageClient {
    @PostMapping("/storage/updatePurchase")
    void updatePurchase(@RequestBody Map<String,String> updateKafkaMap);
}

