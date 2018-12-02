package com.coding.commons.domain.user.repository;

import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.base.data.jpa.repository.CacheRepository;
import com.coding.commons.base.data.redis.RedisUtils;
import com.coding.commons.domain.user.model.UserDeliveryInfo;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class UserDeliveryInfoCacheRepository implements CacheRepository<UserDeliveryInfo, Long> {

    @Inject
    private RedisUtils redisUtils;

    @Inject
    private UserDeliveryInfoRepository userDeliveryInfoRepository;

    @Override
    public Class<UserDeliveryInfo> getEntityClass() {
        return UserDeliveryInfo.class;
    }

    @Override
    public JpaRepository<UserDeliveryInfo, Long> getJpaRepository() {
        return userDeliveryInfoRepository;
    }

    @Override
    public RedisUtils getRedisUtils() {
        return redisUtils;
    }
}
