package org.examproject.service;

import java.util.Map;

import org.examproject.model.Response;
import org.examproject.model.Order;

/**
 * @author h.adachi
 */
public interface OrderService {

    public void send(Order order);

    public void updateBy(Response response);

    public Map<String, Order> getAll();
}
