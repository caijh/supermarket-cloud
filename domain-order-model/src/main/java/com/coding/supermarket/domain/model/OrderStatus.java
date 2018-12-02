package com.coding.supermarket.domain.model;

import com.coding.commons.base.IndexEnum;

public enum OrderStatus implements IndexEnum {
    UN_PAY(0),
    UN_SEND(1),
    UN_RECEIVE(2),
    DONE(3),
    CLOSE(4);

    private int index;

    OrderStatus(int index) {
        this.index = index;
    }

    @Override
    public int getIndex() {
        return this.index;
    }
}
