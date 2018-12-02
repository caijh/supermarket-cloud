package com.coding.commons.domain.resource.repository;

import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.base.data.jpa.repository.CacheRepository;
import com.coding.commons.base.data.redis.RedisUtils;
import com.coding.commons.domain.resource.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class ResourceCacheRepository implements CacheRepository<Resource, Long> {

    @Inject
    private RedisUtils redisUtils;

    @Inject
    private ResourceRepository resourceRepository;

    @Override
    public Class<Resource> getEntityClass() {
        return Resource.class;
    }

    @Override
    public JpaRepository<Resource, Long> getJpaRepository() {
        return resourceRepository;
    }

    @Override
    public RedisUtils getRedisUtils() {
        return redisUtils;
    }
}
