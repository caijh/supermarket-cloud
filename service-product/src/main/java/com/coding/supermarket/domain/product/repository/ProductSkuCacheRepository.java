package com.coding.supermarket.domain.product.repository;

import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.base.data.jpa.repository.CacheRepository;
import com.coding.commons.base.data.redis.RedisUtils;
import com.coding.supermarket.domain.product.model.ProductSku;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class ProductSkuCacheRepository implements CacheRepository<ProductSku, Long> {

    @Inject
    private ProductSkuRepository productSkuRepository;

    @Inject
    private RedisUtils redisUtils;

    @Override
    public Class<ProductSku> getEntityClass() {
        return ProductSku.class;
    }

    @Override
    public JpaRepository<ProductSku, Long> getJpaRepository() {
        return productSkuRepository;
    }

    @Override
    public RedisUtils getRedisUtils() {
        return redisUtils;
    }

}
