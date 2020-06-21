package org.examproject.service;

import java.util.Map;

import org.examproject.model.Order;

/**
 * @author h.adachi
 */
public interface OrderRepository {

    public void put(Order order);

    public Order getById(String orderId);

    public Map<String, Order> getAll();
}
