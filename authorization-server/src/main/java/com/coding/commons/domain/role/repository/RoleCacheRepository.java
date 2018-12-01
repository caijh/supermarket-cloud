package com.coding.commons.domain.role.repository;

import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.base.data.jpa.repository.CacheRepository;
import com.coding.commons.base.data.redis.RedisUtils;
import com.coding.commons.domain.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class RoleCacheRepository implements CacheRepository<Role, Long> {

    @Inject
    private RedisUtils redisUtils;

    @Inject
    private RoleRepository roleRepository;

    @Override
    public Class<Role> getEntityClass() {
        return Role.class;
    }

    @Override
    public JpaRepository<Role, Long> getJpaRepository() {
        return roleRepository;
    }

    @Override
    public RedisUtils getRedisUtils() {
        return redisUtils;
    }
}
