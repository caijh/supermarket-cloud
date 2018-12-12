package com.coding.supermarket.domain.express.exception;

public class ExpressRouteException extends Exception {

    public ExpressRouteException(String message) {
        super(message);
    }

    public ExpressRouteException(String message, Exception e) {
        super(message, e);
    }
}
