package com.test.order.controller;

import com.test.order.pojo.Order;
import com.test.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/createOrder")
    public String createOrder(Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/getOrders")
    public String getOrders() {
        return orderService.getOrderList();
    }

}

