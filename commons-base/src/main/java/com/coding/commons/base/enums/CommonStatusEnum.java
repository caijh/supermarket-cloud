package com.coding.commons.base.enums;

public enum CommonStatusEnum implements IndexEnum {
    IN_USE(0),
    DELETED(1);

    private Integer index;

    CommonStatusEnum(int index) {
        this.index = index;
    }

    @Override
    public int getIndex() {
        return this.index;
    }
}
