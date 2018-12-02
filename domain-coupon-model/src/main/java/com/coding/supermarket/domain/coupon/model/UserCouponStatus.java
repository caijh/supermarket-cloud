package com.coding.supermarket.domain.coupon.model;

import com.coding.commons.base.IndexEnum;

public enum UserCouponStatus implements IndexEnum {
    NON_USE(0),
    FROZEN(1),
    USED(2),
    EXPIRED(3);

    private int index;

    UserCouponStatus(int index) {
        this.index = index;
    }

    @Override
    public int getIndex() {
        return index;
    }
}
