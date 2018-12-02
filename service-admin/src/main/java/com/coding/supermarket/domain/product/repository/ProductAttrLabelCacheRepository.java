package com.coding.supermarket.domain.product.repository;

import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.base.data.jpa.repository.CacheRepository;
import com.coding.commons.base.data.redis.RedisUtils;
import com.coding.supermarket.domain.product.model.ProductAttrLabel;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class ProductAttrLabelCacheRepository implements CacheRepository<ProductAttrLabel, String> {

    @Inject
    private RedisUtils redisUtils;

    @Inject
    private ProductAttrLabelRepository productAttrLabelRepository;

    @Override
    public Class<ProductAttrLabel> getEntityClass() {
        return ProductAttrLabel.class;
    }

    @Override
    public JpaRepository<ProductAttrLabel, String> getJpaRepository() {
        return productAttrLabelRepository;
    }

    @Override
    public RedisUtils getRedisUtils() {
        return redisUtils;
    }
}
