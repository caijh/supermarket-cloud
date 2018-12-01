package com.coding.gateway.context;

public class ThreadLocalHolder {
    private static final ThreadLocal<String> localClientId = new ThreadLocal<>();

    private ThreadLocalHolder() {

    }

    public static String getClientId() {
        return localClientId.get();
    }

    public static void setClientId(String clientId) {
        localClientId.set(clientId);
    }
}
