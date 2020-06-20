package org.examproject.service;

import java.util.Map;

import org.examproject.model.InventoryResponse;
import org.examproject.model.Order;

public interface OrderService {

    public void sendOrder(Order order);

    public void updateOrder(InventoryResponse response);

    public Map<String, Order> getAllOrders();
}
