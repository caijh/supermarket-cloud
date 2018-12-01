package com.coding.commons.base.data.redis;

public class SerializeException extends RuntimeException {
    public SerializeException(String message, Exception e) {
        super(message, e);
    }
}
