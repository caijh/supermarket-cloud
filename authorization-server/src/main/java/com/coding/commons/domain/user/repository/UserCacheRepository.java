package com.coding.commons.domain.user.repository;

import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.base.data.jpa.repository.CacheRepository;
import com.coding.commons.base.data.redis.RedisUtils;
import com.coding.commons.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class UserCacheRepository implements CacheRepository<User, Long> {

    @Inject
    private RedisUtils redisUtils;

    @Inject
    private UserRepository userRepository;

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public JpaRepository<User, Long> getJpaRepository() {
        return userRepository;
    }

    @Override
    public RedisUtils getRedisUtils() {
        return redisUtils;
    }

    public User findByAccount(String username) {
        return userRepository.findByAccount(username);
    }
}
