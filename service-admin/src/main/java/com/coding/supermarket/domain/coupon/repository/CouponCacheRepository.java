package com.coding.supermarket.domain.coupon.repository;

import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.base.data.jpa.repository.CacheRepository;
import com.coding.commons.base.data.redis.RedisUtils;
import com.coding.supermarket.domain.coupon.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class CouponCacheRepository implements CacheRepository<Coupon, Long> {

    @Inject
    private RedisUtils redisUtils;

    @Inject
    private CouponRepository couponRepository;

    @Override
    public Class<Coupon> getEntityClass() {
        return Coupon.class;
    }

    @Override
    public JpaRepository<Coupon, Long> getJpaRepository() {
        return couponRepository;
    }

    @Override
    public RedisUtils getRedisUtils() {
        return redisUtils;
    }
}
