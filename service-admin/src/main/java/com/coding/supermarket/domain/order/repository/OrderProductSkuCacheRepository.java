package com.coding.supermarket.domain.order.repository;

import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.base.data.jpa.repository.CacheRepository;
import com.coding.commons.base.data.redis.RedisUtils;
import com.coding.supermarket.domain.order.model.OrderProductSku;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class OrderProductSkuCacheRepository implements CacheRepository<OrderProductSku, Long> {

    @Inject
    private RedisUtils redisUtils;

    @Inject
    private OrderProductSkuRepository orderProductSkuRepository;

    @Override
    public Class<OrderProductSku> getEntityClass() {
        return OrderProductSku.class;
    }

    @Override
    public JpaRepository<OrderProductSku, Long> getJpaRepository() {
        return orderProductSkuRepository;
    }

    @Override
    public RedisUtils getRedisUtils() {
        return redisUtils;
    }
}
