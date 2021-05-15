package com.github.caijh.supermarket.coupon.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

/**
 * 优惠券适用的商品.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS)
@Data
public class CouponApplyProduct implements CouponApplyRange {

    private static final long serialVersionUID = 3981553449799426324L;
    private List<Long> productIds;

}
