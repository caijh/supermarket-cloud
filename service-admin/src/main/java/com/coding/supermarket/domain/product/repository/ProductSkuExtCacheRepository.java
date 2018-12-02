package com.coding.supermarket.domain.product.repository;

import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.base.data.jpa.repository.CacheRepository;
import com.coding.commons.base.data.redis.RedisUtils;
import com.coding.supermarket.domain.product.model.ProductSkuExt;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class ProductSkuExtCacheRepository implements CacheRepository<ProductSkuExt, Long> {

    @Inject
    private RedisUtils redisUtils;

    @Inject
    private ProductSkuExtRepository productSkuExtRepository;

    @Override
    public Class<ProductSkuExt> getEntityClass() {
        return ProductSkuExt.class;
    }

    @Override
    public JpaRepository<ProductSkuExt, Long> getJpaRepository() {
        return this.productSkuExtRepository;
    }

    @Override
    public RedisUtils getRedisUtils() {
        return redisUtils;
    }
}
