package org.examproject.util;

import java.util.UUID;

/**
 * @author h.adachi
 */
public class CommonUtil {
    public static String getUniqueId() {
        return UUID.randomUUID().toString();
    }
}