package com.coding.commons.domain.country.repository;

import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.base.data.jpa.repository.CacheRepository;
import com.coding.commons.base.data.redis.RedisUtils;
import com.coding.commons.domain.country.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class CountryCacheRepository implements CacheRepository<Country, Long> {

    @Inject
    private RedisUtils redisUtils;

    @Inject
    private CountryRepository countryRepository;

    @Override
    public Class<Country> getEntityClass() {
        return Country.class;
    }

    @Override
    public JpaRepository<Country, Long> getJpaRepository() {
        return countryRepository;
    }

    @Override
    public RedisUtils getRedisUtils() {
        return redisUtils;
    }
}
