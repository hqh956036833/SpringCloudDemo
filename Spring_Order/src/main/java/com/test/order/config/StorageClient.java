package com.test.order.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("storage")
public interface StorageClient {
    @GetMapping("/storage/reduceStorage/{productId}")
    String reduceStorage(@PathVariable("productId") Integer productId);
}

