package com.coding.supermarket.domain.product.repository;

import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.base.data.jpa.repository.CacheRepository;
import com.coding.commons.base.data.redis.RedisUtils;
import com.coding.supermarket.domain.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class ProductCacheRepository implements CacheRepository<Product, Long> {

    @Inject
    private RedisUtils redisUtils;

    @Inject
    private ProductRepository productRepository;

    @Override
    public Class<Product> getEntityClass() {
        return Product.class;
    }

    @Override
    public JpaRepository<Product, Long> getJpaRepository() {
        return productRepository;
    }

    @Override
    public RedisUtils getRedisUtils() {
        return redisUtils;
    }
}
