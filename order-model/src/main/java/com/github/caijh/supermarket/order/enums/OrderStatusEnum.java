package com.github.caijh.supermarket.order.enums;


import com.github.caijh.framework.core.enums.IndexEnum;

public enum OrderStatusEnum implements IndexEnum {
    UN_PAY(0),
    UN_SEND(1),
    UN_RECEIVE(2),
    DONE(3),
    CLOSE(4);

    private final int index;

    OrderStatusEnum(int index) {
        this.index = index;
    }

    @Override
    public int getIndex() {
        return this.index;
    }
}
