package com.coding.supermarket.domain.coupon.repository;

import com.coding.supermarket.domain.coupon.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
