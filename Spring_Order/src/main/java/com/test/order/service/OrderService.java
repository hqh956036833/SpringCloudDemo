package com.test.order.service;

import com.test.order.pojo.Order;

import java.util.Map;


public interface OrderService {
    String createOrder(Order order);

    String getOrderList();

    Map<String, Object> sendMessage(String topic, Map<String, Object> message);
}

