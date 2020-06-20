package com.studio.meowtoon.service;

import java.util.Map;

import com.studio.meowtoon.model.Order;

public interface OrderRepository {

    public void putOrder(Order order);

    public Order getOrder(String orderId);

    public Map<String, Order> getAllOrders();
}
