package com.coding.supermarket.domain.product.repository;

import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.base.data.jpa.repository.CacheRepository;
import com.coding.commons.base.data.redis.RedisUtils;
import com.coding.supermarket.domain.product.model.CategoryProductAttr;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class CategoryProductAttrCacheRepository implements CacheRepository<CategoryProductAttr, Long> {

    @Inject
    private RedisUtils redisUtils;

    @Inject
    private CategoryProductAttrRepository categoryProductAttrRepository;

    @Override
    public Class<CategoryProductAttr> getEntityClass() {
        return CategoryProductAttr.class;
    }

    @Override
    public JpaRepository<CategoryProductAttr, Long> getJpaRepository() {
        return categoryProductAttrRepository;
    }

    @Override
    public RedisUtils getRedisUtils() {
        return redisUtils;
    }

}
