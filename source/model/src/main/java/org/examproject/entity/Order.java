package org.examproject.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import org.springframework.context.annotation.Scope;

/**
 * @author h.adachi
 */
@Data
@Entity
@Table(name="orders")
@Scope(value="prototype")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    Long id;

    @Column(name="order_id", unique=true)
    private String orderId;

    @Column(name="product_name", unique=true, length=32)
    private String productName;

    @Column(name="quantity")
    private int quantity;

    @Column(name="status")
    private String status;

}
