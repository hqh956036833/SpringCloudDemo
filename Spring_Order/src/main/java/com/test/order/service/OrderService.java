package com.test.order.service;

import com.test.order.pojo.Order;


public interface OrderService {
    String createOrder(Order order);

    String getOrderList();

}

