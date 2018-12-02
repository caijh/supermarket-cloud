package com.coding.commons.domain.user.repository;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.base.data.jpa.repository.CacheRepository;
import com.coding.commons.base.data.redis.RedisUtils;
import com.coding.commons.domain.user.model.UserRole;
import com.coding.commons.domain.user.model.UserRoleId;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class UserRoleCacheRepository implements CacheRepository<UserRole, UserRoleId> {

    @Inject
    private RedisUtils redisUtils;

    @Inject
    private UserRoleRepository userRoleRepository;

    @Override
    public Class<UserRole> getEntityClass() {
        return UserRole.class;
    }

    @Override
    public JpaRepository<UserRole, UserRoleId> getJpaRepository() {
        return userRoleRepository;
    }

    @Override
    public RedisUtils getRedisUtils() {
        return redisUtils;
    }

    public List<UserRole> findByRoleId(Long roleId, Pageable pageable) {
        return findAll(Example.of(UserRole.builder().roleId(roleId).build()), pageable).getContent();
    }

}
