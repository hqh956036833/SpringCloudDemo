package com.test.order.controller;

import com.test.order.pojo.Order;
import com.test.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    @GetMapping("/purchase")
    @ResponseBody
    public void purchase(Integer number, String id) throws Exception{

        // 提交Callable任务给ExecutorService
        Map<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("number",number);
        objectObjectHashMap.put("id",id);
        orderService.sendMessage("demo",objectObjectHashMap);

    }



}

