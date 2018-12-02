package com.coding.commons.domain.area.repository;

import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.base.data.jpa.repository.CacheRepository;
import com.coding.commons.base.data.redis.RedisUtils;
import com.coding.commons.domain.area.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class AreaCacheRepository implements CacheRepository<Area, String> {

    @Inject
    private RedisUtils redisUtils;

    @Inject
    private AreaRepository areaRepository;

    @Override
    public Class<Area> getEntityClass() {
        return Area.class;
    }

    @Override
    public JpaRepository<Area, String> getJpaRepository() {
        return areaRepository;
    }

    @Override
    public RedisUtils getRedisUtils() {
        return redisUtils;
    }
}
