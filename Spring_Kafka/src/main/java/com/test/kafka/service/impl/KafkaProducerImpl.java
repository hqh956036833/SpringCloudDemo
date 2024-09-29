package com.test.kafka.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.kafka.service.KafkaProducerService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class KafkaProducerImpl implements KafkaProducerService {

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> sendMessage(String topic, Map<String, Object> message) {
        try {
            String valueAsString = new ObjectMapper().writeValueAsString(message);
            // 异步
            // kafkaTemplate.send(topic, valueAsString);
            // 同步：get() 获取执行结果，此时线程将阻塞，等待执行结果
            SendResult<String, Object> sendResult = kafkaTemplate.send(topic, valueAsString).get();
            sendResult.toString();
            message.put("sendResult", sendResult.toString());
            // org.apache.kafka.common.errors.TimeoutException: Failed to update metadata after 60000 ms.
        } catch (Exception e) {
            // 异步发送时子线程中的异常是不会进入这里的，只有同步发送时，主线程阻塞，发送是吧，抛出异常时，才会进入这里。
            e.printStackTrace();
        }
        return message;
    }
}
