package org.examproject.model;

import java.io.Serializable;

import lombok.Data;

/**
 * @author h.adachi
 */
@Data
public class Order implements Serializable {

    private String orderId;

    private String productName;

    private int quantity;

    private OrderStatus status;

}
