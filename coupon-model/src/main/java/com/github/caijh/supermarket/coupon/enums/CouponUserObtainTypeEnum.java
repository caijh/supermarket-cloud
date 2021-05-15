package com.github.caijh.supermarket.coupon.enums;

import com.github.caijh.framework.core.enums.IndexEnum;

public enum CouponUserObtainTypeEnum implements IndexEnum {
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

    private final int index;

    CouponUserObtainTypeEnum(int index) {
        this.index = index;
    }

    @Override
    public int getIndex() {
        return this.index;
    }
}
