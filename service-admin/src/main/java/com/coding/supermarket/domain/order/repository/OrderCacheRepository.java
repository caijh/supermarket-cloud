package com.coding.supermarket.domain.order.repository;

import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.base.data.jpa.repository.CacheRepository;
import com.coding.commons.base.data.redis.RedisUtils;
import com.coding.supermarket.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class OrderCacheRepository implements CacheRepository<Order, Long> {

    @Inject
    private RedisUtils redisUtils;

    @Inject
    private OrderRepository orderRepository;

    @Override
    public Class<Order> getEntityClass() {
        return Order.class;
    }

    @Override
    public JpaRepository<Order, Long> getJpaRepository() {
        return this.orderRepository;
    }

    @Override
    public RedisUtils getRedisUtils() {
        return redisUtils;
    }
}
