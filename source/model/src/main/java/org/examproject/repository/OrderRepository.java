package org.examproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.examproject.entity.Order;

/**
 * @author h.adachi
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    public Order findByOrderId(String orderId);
    
}
