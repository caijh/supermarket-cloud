package com.coding.supermarket.domain.product.repository;

import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.base.data.jpa.repository.CacheRepository;
import com.coding.commons.base.data.redis.RedisUtils;
import com.coding.supermarket.domain.product.model.ShoppingCartSku;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class ShoppingCartCacheRepository implements CacheRepository<ShoppingCartSku, Long> {

    @Inject
    private RedisUtils redisUtils;

    @Inject
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public Class<ShoppingCartSku> getEntityClass() {
        return ShoppingCartSku.class;
    }

    @Override
    public JpaRepository<ShoppingCartSku, Long> getJpaRepository() {
        return shoppingCartRepository;
    }

    @Override
    public RedisUtils getRedisUtils() {
        return redisUtils;
    }
}
