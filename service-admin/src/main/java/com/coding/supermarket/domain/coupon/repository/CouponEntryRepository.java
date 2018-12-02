package com.coding.supermarket.domain.coupon.repository;

import com.coding.supermarket.domain.coupon.model.CouponEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponEntryRepository extends JpaRepository<CouponEntry, Long> {
}
