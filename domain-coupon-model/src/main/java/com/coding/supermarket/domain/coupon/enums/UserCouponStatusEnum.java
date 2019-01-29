package com.coding.supermarket.domain.coupon.enums;

import com.coding.commons.base.IndexEnum;

public enum UserCouponStatusEnum implements IndexEnum {
    NON_USE(0),
    FROZEN(1),
    USED(2),
    EXPIRED(3);

    private int index;

    UserCouponStatusEnum(int index) {
        this.index = index;
    }

    @Override
    public int getIndex() {
        return index;
    }
}
