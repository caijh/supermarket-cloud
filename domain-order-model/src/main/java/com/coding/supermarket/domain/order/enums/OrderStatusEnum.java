package com.coding.supermarket.domain.order.enums;

import com.coding.commons.base.enums.IndexEnum;

public enum OrderStatusEnum implements IndexEnum {
    UN_PAY(0),
    UN_SEND(1),
    UN_RECEIVE(2),
    DONE(3),
    CLOSE(4);

    private int index;

    OrderStatusEnum(int index) {
        this.index = index;
    }

    @Override
    public int getIndex() {
        return this.index;
    }
}
