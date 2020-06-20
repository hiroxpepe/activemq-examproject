package com.studio.meowtoon.util;

import java.util.UUID;

public class BasicUtil {

    public static String getUniqueId() {
        return UUID.randomUUID().toString();
    }
}
