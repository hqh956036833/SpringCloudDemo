package com.test.order.service.impl;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.test.order.mapper.OrderMapper;
import com.test.order.pojo.Order;
import com.test.order.service.OrderService;
import com.test.order.config.StorageClient;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private StorageClient storageClient;

    @Autowired
    private OrderMapper orderMapper;

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

}


