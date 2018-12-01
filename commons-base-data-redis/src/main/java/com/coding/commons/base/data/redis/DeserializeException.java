package com.coding.commons.base.data.redis;

public class DeserializeException extends RuntimeException {
    public DeserializeException(Exception e) {
        super(e);
    }
}
