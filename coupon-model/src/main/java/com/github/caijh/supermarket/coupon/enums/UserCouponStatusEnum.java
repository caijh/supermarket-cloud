package com.github.caijh.supermarket.coupon.enums;

import com.github.caijh.framework.core.enums.IndexEnum;

public enum UserCouponStatusEnum implements IndexEnum {
    NON_USE(0),
    FROZEN(1),
    USED(2),
    EXPIRED(3);

    private final int index;

    UserCouponStatusEnum(int index) {
        this.index = index;
    }

    @Override
    public int getIndex() {
        return this.index;
    }
}
