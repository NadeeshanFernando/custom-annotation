/**
 * Copyright (c) 2025 nadeeshan_fdz. All rights reserved.
 * <p>
 * This file is part of the Spring Boot Project and may not be
 * copied, modified, or distributed without permission.
 * <p>
 * Author: nadeeshan_fdz
 * Date: 11/05/2025
 */

package com.anton.custom_annotation.service.impl;

import com.anton.custom_annotation.annotation.MyLogger;
import com.anton.custom_annotation.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Author by nadeeshan_fdz, Date = "11/05/2025"
 */
@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Override
    @MyLogger
    public String placeOrder(String productId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        log.info("Order placed for product: " + productId + ", quantity: " + quantity);
        return "Order placed for product: " + productId + ", quantity: " + quantity;
    }
}
