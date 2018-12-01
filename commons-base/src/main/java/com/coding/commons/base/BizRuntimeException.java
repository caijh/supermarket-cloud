package com.coding.commons.base;

public class BizRuntimeException extends RuntimeException {

    public BizRuntimeException(Exception e) {
        super(e);
    }

    public BizRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
