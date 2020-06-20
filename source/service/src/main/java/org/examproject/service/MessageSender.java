
package org.examproject.service;

/**
 *
 * @author hiroxpepe
 */
public interface MessageSender<T> {

    void sendMessage(T type);

}
