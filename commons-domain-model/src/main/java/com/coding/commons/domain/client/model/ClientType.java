package com.coding.commons.domain.client.model;

import com.coding.commons.base.IndexEnum;

public enum ClientType implements IndexEnum {
    PUBLIC(0), CONFIDENTIAL(1);

    private int index;

    ClientType(int index) {
        this.index = index;
    }

    @Override
    public int getIndex() {
        return this.index;
    }
}
