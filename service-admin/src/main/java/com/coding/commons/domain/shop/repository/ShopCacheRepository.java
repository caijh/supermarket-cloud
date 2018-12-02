package com.coding.commons.domain.shop.repository;

import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.base.data.jpa.repository.CacheRepository;
import com.coding.commons.base.data.redis.RedisUtils;
import com.coding.commons.domain.shop.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class ShopCacheRepository implements CacheRepository<Shop, Long> {

    @Inject
    private RedisUtils redisUtils;

    @Inject
    private ShopRepository shopRepository;

    @Override
    public Class<Shop> getEntityClass() {
        return Shop.class;
    }

    @Override
    public JpaRepository<Shop, Long> getJpaRepository() {
        return shopRepository;
    }

    @Override
    public RedisUtils getRedisUtils() {
        return redisUtils;
    }
}
