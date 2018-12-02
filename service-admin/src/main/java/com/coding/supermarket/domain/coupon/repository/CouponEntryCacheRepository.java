package com.coding.supermarket.domain.coupon.repository;

import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.base.data.jpa.repository.CacheRepository;
import com.coding.commons.base.data.redis.RedisUtils;
import com.coding.supermarket.domain.coupon.model.CouponEntry;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class CouponEntryCacheRepository implements CacheRepository<CouponEntry, Long> {

    @Inject
    private RedisUtils redisUtils;

    @Inject
    private CouponEntryRepository couponEntryRepository;

    @Override
    public Class<CouponEntry> getEntityClass() {
        return CouponEntry.class;
    }

    @Override
    public JpaRepository<CouponEntry, Long> getJpaRepository() {
        return this.couponEntryRepository;
    }

    @Override
    public RedisUtils getRedisUtils() {
        return redisUtils;
    }
}
