package com.coding.commons.domain.client.repository;

import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.base.data.jpa.repository.CacheRepository;
import com.coding.commons.base.data.redis.RedisUtils;
import com.coding.commons.domain.client.model.ClientApp;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class ClientAppCacheRepository implements CacheRepository<ClientApp, String> {
    @Inject
    private RedisUtils redisUtils;

    @Inject
    private ClientAppRepository clientAppRepository;

    @Override
    public Class<ClientApp> getEntityClass() {
        return ClientApp.class;
    }

    @Override
    public JpaRepository<ClientApp, String> getJpaRepository() {
        return clientAppRepository;
    }

    @Override
    public RedisUtils getRedisUtils() {
        return redisUtils;
    }
}
