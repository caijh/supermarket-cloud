package com.github.caijh.supermarket.coupon.model;

import java.io.Serializable;

public interface CouponApplyRange extends Serializable {

    default String getType() {
        return this.getClass().getSimpleName();
    }

}
