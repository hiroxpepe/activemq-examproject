package org.examproject.model;

import java.io.Serializable;

import lombok.Data;

/**
 * @author h.adachi
 */
@Data
public class Response implements Serializable {

    private String orderId;

    private int returnCode;

    private String comment;

}
