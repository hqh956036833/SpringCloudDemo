package com.test.order.service.impl;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.order.config.KafkaClient;
import com.test.order.mapper.OrderMapper;
import com.test.order.pojo.Order;
import com.test.order.service.OrderService;
import com.test.order.config.StorageClient;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;
import java.util.Map;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private StorageClient storageClient;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private KafkaClient kafkaClient;

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    @GlobalTransactional
    @Override
    public String createOrder(Order order) {
        // 创建订单
        order.setOrderNumber(UUID.randomUUID().toString());
        orderMapper.insert(order);
        // 扣减库存
        String reduceRes = storageClient.reduceStorage(order.getProductId());
        return "创建订单成功 == " + reduceRes;
    }

    @SentinelResource(value = "getOrderList",blockHandler ="getOrderListBlockHandler") // 指定资源名并标识资源是否被限流、降级
    @Override
    public String getOrderList() {
        return "返回订单列表";
    }

    /**
     * 编写熔断降级提示
     * @param be
     * @return
     */
    public String getOrderListBlockHandler(BlockException be) {
        be.printStackTrace();
        return "触发熔断降级处理规则";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> sendMessage(String topic, Map<String, Object> message) {
        logger.info("向指定主题发送信息，topic={},message={}", topic, message);
        return kafkaClient.sendMessage(topic,message);

    }

}


