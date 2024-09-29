package com.test.kafka.controller;

import com.test.kafka.config.StorageClient;
import com.test.kafka.service.KafkaConsumerService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class KafkaConsumerController {

    @Autowired
    private KafkaConsumerService kafkaConsumerService;

//    @Autowired
//    private StorageClient storageClient;
//    @PostMapping("kafka/messageListener1")
//    @ResponseBody
//    public void messageListener1(ConsumerRecord<?, ?> record){
//        Map<String, String> stringStringMap = kafkaConsumerService.messageListener1(record);
//        storageClient.updatePurchase(stringStringMap);
//    }
}
