package com.coding.commons.domain.role.repository;

import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.base.data.jpa.repository.CacheRepository;
import com.coding.commons.base.data.redis.RedisUtils;
import com.coding.commons.domain.role.model.RoleResource;
import com.coding.commons.domain.role.model.RoleResourceId;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class RoleResourceCacheRepository implements CacheRepository<RoleResource, RoleResourceId> {

    @Inject
    private RedisUtils redisUtils;

    @Inject
    private RoleResourceRepository roleResourceRepository;

    @Override
    public Class<RoleResource> getEntityClass() {
        return null;
    }

    @Override
    public JpaRepository<RoleResource, RoleResourceId> getJpaRepository() {
        return roleResourceRepository;
    }

    @Override
    public RedisUtils getRedisUtils() {
        return redisUtils;
    }

    public List<RoleResource> findAllByRoleIdIn(Set<Long> roleIds) {
        return roleResourceRepository.findAllByRoleIdIn(roleIds);
    }

    public List<RoleResource> findAllByRoleId(Long roleId) {
        return roleResourceRepository.findAllByRoleId(roleId);
    }
}
