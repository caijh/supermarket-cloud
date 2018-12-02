package com.coding.supermarket.domain.coupon.model;

import com.coding.commons.base.IndexEnum;

public enum CouponUserObtainType implements IndexEnum {
    /**
     * 用户领取.
     */
    OBTAIN_SELF(0),
    /**
     * 节点发放.
     */
    SEND_EVENT(1),
    /**
     * 指定用户发放.
     */
    SEND_SPECIFY_USER(2);

    private Integer index;

    CouponUserObtainType(int index) {
        this.index = index;
    }

    @Override
    public int getIndex() {
        return this.index;
    }
}
