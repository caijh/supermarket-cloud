package com.github.caijh.supermarket.coupon.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

/**
 * 优惠券适用的分类.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS)
@Data
public class CouponApplyCategory implements CouponApplyRange {

    private static final long serialVersionUID = 1101029811608205378L;
    private List<Long> categoryIds;

    private List<Long> excludeBrandIds;

    private List<Long> excludeProductIds;

}
