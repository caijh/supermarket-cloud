package com.coding.supermarket.domain.product.repository;

import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.base.data.jpa.repository.CacheRepository;
import com.coding.commons.base.data.redis.RedisUtils;
import com.coding.supermarket.domain.product.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

@Named("productCategoryCacheRepository")
public class CategoryCacheRepository implements CacheRepository<Category, Long> {

    @Inject
    private RedisUtils redisUtils;

    @Inject
    private CategoryRepository categoryRepository;

    @Override
    public Class<Category> getEntityClass() {
        return Category.class;
    }

    @Override
    public JpaRepository<Category, Long> getJpaRepository() {
        return categoryRepository;
    }

    @Override
    public RedisUtils getRedisUtils() {
        return redisUtils;
    }
}
