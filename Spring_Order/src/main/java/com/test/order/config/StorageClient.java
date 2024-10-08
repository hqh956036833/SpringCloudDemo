package com.test.order.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient("storage")
public interface StorageClient {
    @GetMapping("/storage/reduceStorage/{productId}")
    String reduceStorage(@PathVariable("productId") Integer productId);

    @PostMapping("/storage/updatePurchase")
    String updatePurchase(@RequestBody Map<String,String> updateKafkaMap);
}

