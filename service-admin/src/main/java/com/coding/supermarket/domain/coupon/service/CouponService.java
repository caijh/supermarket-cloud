package com.coding.supermarket.domain.coupon.service;

import com.coding.commons.base.BizException;
import com.coding.commons.base.data.jpa.service.JpaBaseService;
import com.coding.supermarket.domain.coupon.model.Coupon;
import com.coding.supermarket.domain.coupon.model.CouponEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CouponService extends JpaBaseService<Coupon, Long> {
    void add(Coupon coupon);

    void update(Coupon coupon) throws BizException;

    void delete(Long id) throws BizException;

    Page<CouponEntry> listEntry(CouponEntry couponEntry, Pageable pageable);
}
