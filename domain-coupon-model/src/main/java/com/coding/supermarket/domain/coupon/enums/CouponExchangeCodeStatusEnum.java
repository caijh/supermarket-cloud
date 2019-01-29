package com.coding.supermarket.domain.coupon.enums;

import com.coding.commons.base.IndexEnum;

public enum CouponExchangeCodeStatusEnum implements IndexEnum {
    NOT_USE(0),
    USED(1);

    private Integer index;

    CouponExchangeCodeStatusEnum(int index) {
        this.index = index;
    }

    @Override
    public int getIndex() {
        return this.index;
    }
}
