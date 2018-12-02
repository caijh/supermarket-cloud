package com.coding.supermarket.domain.coupon.repository;

import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.base.data.jpa.repository.CacheRepository;
import com.coding.commons.base.data.redis.RedisUtils;
import com.coding.supermarket.domain.coupon.model.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class UserCouponCacheRepository implements CacheRepository<UserCoupon, Long> {

    @Inject
    private RedisUtils redisUtils;

    @Inject
    private UserCouponRepository userCouponRepository;

    @Override
    public Class<UserCoupon> getEntityClass() {
        return UserCoupon.class;
    }

    @Override
    public JpaRepository<UserCoupon, Long> getJpaRepository() {
        return userCouponRepository;
    }

    @Override
    public RedisUtils getRedisUtils() {
        return redisUtils;
    }
}
