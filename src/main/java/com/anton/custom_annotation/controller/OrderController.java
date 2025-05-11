/**
 * Copyright (c) 2025 nadeeshan_fdz. All rights reserved.
 * <p>
 * This file is part of the Spring Boot Project and may not be
 * copied, modified, or distributed without permission.
 * <p>
 * Author: nadeeshan_fdz
 * Date: 11/05/2025
 */

package com.anton.custom_annotation.controller;

import com.anton.custom_annotation.annotation.MyLogger;
import com.anton.custom_annotation.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author by nadeeshan_fdz, Date = "11/05/2025"
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @MyLogger
    public String placeOrder(@RequestParam String productId, @RequestParam int quantity) {
        log.info("Controller: received API call");
        return orderService.placeOrder(productId, quantity);
    }
}
