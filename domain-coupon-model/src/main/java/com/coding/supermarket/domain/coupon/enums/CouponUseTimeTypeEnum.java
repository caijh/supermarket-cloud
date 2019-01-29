package com.coding.supermarket.domain.coupon.enums;

import com.coding.commons.base.IndexEnum;

public enum CouponUseTimeTypeEnum implements IndexEnum {
    /**
     * 在开始与结束时间内使用.
     */
    START_END_TIME(0),

    /**
     * 领券后一段时间内使用.
     */
    RECEIVE_TIME(1);

    private Integer index;

    CouponUseTimeTypeEnum(Integer index) {
        this.index = index;
    }

    @Override
    public int getIndex() {
        return index;
    }
}
