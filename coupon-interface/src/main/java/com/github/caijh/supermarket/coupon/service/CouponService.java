package com.github.caijh.supermarket.coupon.service;

import com.github.caijh.supermarket.base.service.BaseService;
import com.github.caijh.supermarket.coupon.model.Coupon;
import com.github.caijh.supermarket.coupon.model.CouponEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CouponService extends BaseService<Coupon, Long> {

    Page<CouponEntry> listEntry(CouponEntry couponEntry, Pageable pageable);

}
