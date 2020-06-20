package com.studio.meowtoon.service;

import java.util.Map;

import com.studio.meowtoon.model.InventoryResponse;
import com.studio.meowtoon.model.Order;

public interface OrderService {

    public void sendOrder(Order order);

    public void updateOrder(InventoryResponse response);

    public Map<String, Order> getAllOrders();
}
