package com.coding.supermarket.domain.coupon.model;

import com.coding.commons.base.IndexEnum;

public enum CouponExchangeCodeStatus implements IndexEnum {
    NOT_USE(0),
    USED(1);

    private Integer index;

    CouponExchangeCodeStatus(int index) {
        this.index = index;
    }

    @Override
    public int getIndex() {
        return this.index;
    }
}
