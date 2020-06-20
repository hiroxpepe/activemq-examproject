package org.examproject.service;

import java.util.Map;

import org.examproject.model.Order;

/**
 * @author h.adachi
 */
public interface OrderRepository {

    public void putOrder(Order order);

    public Order getOrder(String orderId);

    public Map<String, Order> getAllOrders();
}
