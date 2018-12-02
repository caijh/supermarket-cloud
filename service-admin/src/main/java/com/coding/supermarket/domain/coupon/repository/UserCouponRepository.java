package com.coding.supermarket.domain.coupon.repository;

import com.coding.supermarket.domain.coupon.model.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {
}
