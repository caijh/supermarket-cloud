package com.coding.supermarket.domain.coupon.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

/**
 * 优惠券适用的商品.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS)
@Data
public class CouponApplyProduct implements CouponApplyRange {
    private List<Long> productIds;

    @Override
    public String getType() {
        return getClass().getSimpleName();
    }
}
