package com.github.caijh.supermarket.coupon.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

/**
 * 优惠券适用的品牌.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS)
@Data
public class CouponApplyBrand implements CouponApplyRange {

    private static final long serialVersionUID = -4728374628265756033L;
    private List<Long> brandIds;

    private List<Long> excludeProductIds;

}
