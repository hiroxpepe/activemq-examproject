
package org.examproject.service;

/**
 *
 * @author hiroxpepe
 */
public interface MessageSender<T> {

    void send(T type);

}
