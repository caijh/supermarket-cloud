package com.github.caijh.supermarket.coupon.enums;

import com.github.caijh.framework.core.enums.IndexEnum;

public enum CouponExchangeCodeStatusEnum implements IndexEnum {
    NOT_USE(0),
    USED(1);

    private final int index;

    CouponExchangeCodeStatusEnum(int index) {
        this.index = index;
    }

    @Override
    public int getIndex() {
        return this.index;
    }
}
