package com.coding.supermarket.domain.product.repository;

import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.base.data.jpa.repository.CacheRepository;
import com.coding.commons.base.data.redis.RedisUtils;
import com.coding.supermarket.domain.product.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class BrandCacheRepository implements CacheRepository<Brand, String> {

    @Inject
    private RedisUtils redisUtils;

    @Inject
    private BrandRepository brandRepository;

    @Override
    public Class<Brand> getEntityClass() {
        return Brand.class;
    }

    @Override
    public JpaRepository<Brand, String> getJpaRepository() {
        return brandRepository;
    }

    @Override
    public RedisUtils getRedisUtils() {
        return redisUtils;
    }
}
