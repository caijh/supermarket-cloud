package com.github.caijh.supermarket.base;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.persistence.EntityNotFoundException;

import com.github.caijh.framework.core.model.PersistentObject;
import com.github.caijh.framework.data.redis.Redis;
import com.github.caijh.framework.data.redis.util.ProtoStuffSerializerUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CacheRepository<E extends PersistentObject<I>, I extends Serializable> extends
        JpaRepository<E, I> {

    Class<E> getEntityClass();

    JpaRepository<E, I> getJpaRepository();

    Redis getRedis();

    default String getEntityRedisNamespace(Class<?> clazz) {
        return clazz.getSimpleName() + ":";
    }

    default String getEntityRedisKeyPattern() {
        return this.getEntityRedisNamespace(this.getEntityClass()) + "*";
    }

    default String getEntityRedisKey(I id) {
        return this.getEntityRedisNamespace(this.getEntityClass()) + id;
    }

    @Nonnull
    @Override
    default List<E> findAllById(@Nonnull Iterable<I> ids) {
        String key = this.getEntityRedisNamespace(this.getEntityClass()) + "Method:findAllById:" + Strings.join(ids, ',');
        List<E> list = this.getRedis().getList(key);
        if (list != null) {
            return list;
        }
        List<E> entities = this.getJpaRepository().findAllById(ids);
        this.getRedis().setList(key, entities);
        return entities;
    }

    @Nonnull
    @Override
    default List<E> findAll() {
        return this.getJpaRepository().findAll();
    }

    @Nonnull
    @Override
    default List<E> findAll(@Nonnull Sort sort) {
        return this.getJpaRepository().findAll(sort);
    }

    @Nonnull
    @Override
    default Page<E> findAll(@Nonnull Pageable pageable) {
        String key = this.getEntityRedisNamespace(this.getEntityClass()) + "Method:findAll:" + new String(ProtoStuffSerializerUtils
                .serialize(pageable), StandardCharsets.UTF_8);
        Page<E> cache = this.getRedis().get(key);
        if (cache != null) {
            return cache;
        }

        Page<E> page = this.getJpaRepository().findAll(pageable);
        this.getRedis().set(key, page, 30L);
        return page;
    }

    @Nonnull
    @Override
    default <S extends E> List<S> findAll(@Nonnull Example<S> example) {
        Class<S> probeType = example.getProbeType();
        String key = this.getEntityRedisNamespace(probeType) + "Method:findAll:" + new String(ProtoStuffSerializerUtils
                .serialize(example), StandardCharsets.UTF_8);
        List<S> list = this.getRedis().getList(key);
        if (list != null) {
            return list;
        }

        List<S> entities = this.getJpaRepository().findAll(example);
        this.getRedis().setList(key, entities);
        return entities;
    }

    @Nonnull
    @Override
    default <S extends E> List<S> findAll(@Nonnull Example<S> example, @Nonnull Sort sort) {
        String key = this.getEntityRedisNamespace(this.getEntityClass()) + "Method:findAll:"
                + new String(ProtoStuffSerializerUtils.serialize(example), StandardCharsets.UTF_8)
                + new String(ProtoStuffSerializerUtils.serialize(sort), StandardCharsets.UTF_8);
        List<S> list = this.getRedis().getList(key);
        if (list != null) {
            return list;
        }

        List<S> entities = this.getJpaRepository().findAll(example, sort);
        this.getRedis().setList(key, entities);
        return entities;
    }

    @Nonnull
    @Override
    default <S extends E> Page<S> findAll(@Nonnull Example<S> example, @Nonnull Pageable pageable) {
        String key = this.getEntityRedisNamespace(example.getProbeType()) + "Method:findAll:"
                + new String(ProtoStuffSerializerUtils.serialize(example), StandardCharsets.UTF_8)
                + new String(ProtoStuffSerializerUtils.serialize(pageable), StandardCharsets.UTF_8);
        Page<S> cache = this.getRedis().get(key);
        if (cache != null) {
            return cache;
        }

        Page<S> page = this.getJpaRepository().findAll(example, pageable);
        this.getRedis().set(key, page, 30L);
        return page;
    }

    @Override
    default long count() {
        String key = this.getEntityRedisNamespace(this.getEntityClass()) + "Method:count";
        Long cacheCount = this.getRedis().get(key);
        if (cacheCount != null) {
            return cacheCount;
        }
        long count = this.getJpaRepository().count();
        this.getRedis().set(key, count, 15L);
        return count;
    }

    @Override
    default <S extends E> long count(@Nonnull Example<S> example) {
        String key = this.getEntityRedisNamespace(this.getEntityClass()) + "Method:count:" + new String(ProtoStuffSerializerUtils
                .serialize(example), StandardCharsets.UTF_8);
        Long cacheCount = this.getRedis().get(key);
        if (cacheCount != null) {
            return cacheCount;
        }
        long count = this.getJpaRepository().count(example);
        this.getRedis().set(key, count, 15L);
        return count;
    }

    @Override
    default void deleteById(@Nonnull I id) {
        this.getJpaRepository().deleteById(id);
        this.getRedis().delete(this.getEntityRedisKey(id));
    }

    @Override
    default void delete(@Nonnull E entity) {
        this.getJpaRepository().delete(entity);
        this.getRedis().delete(this.getEntityRedisKey(entity.getId()));
    }

    @Override
    default void deleteInBatch(@Nonnull Iterable<E> iterable) {
        this.getJpaRepository().deleteInBatch(iterable);
        List<String> keys = new ArrayList<>();
        List<E> entities = new ArrayList<>();
        iterable.forEach(e -> {
            keys.add(this.getEntityRedisKey(e.getId()));
            entities.add(e);
        });
        this.getRedis().delete(keys);
    }

    @Override
    default void deleteAllInBatch() {
        this.getJpaRepository().deleteAllInBatch();
        this.getRedis().batchDelete(this.getEntityRedisKeyPattern());
    }

    @Override
    default void deleteAll(@Nonnull Iterable<? extends E> iterable) {
        this.getJpaRepository().deleteAll(iterable);
        List<String> keys = new ArrayList<>();
        List<E> entities = new ArrayList<>();
        iterable.forEach(e -> {
            keys.add(this.getEntityRedisNamespace(this.getEntityClass()) + e.getId());
            entities.add(e);
        });
        this.getRedis().delete(keys);
    }

    @Override
    default void deleteAll() {
        this.getJpaRepository().deleteAll();
        this.getRedis().batchDelete(this.getEntityRedisKeyPattern());
    }

    @Nonnull
    @Override
    default <S extends E> List<S> saveAll(@Nonnull Iterable<S> entities) {
        List<S> list = this.getJpaRepository().saveAll(entities);
        Map<String, S> map = new HashMap<>();
        list.forEach(e -> map.put(this.getEntityRedisNamespace(e.getClass()) + e.getId(), e));
        this.getRedis().getRedisTemplate().opsForValue().multiSet(map);
        return list;
    }

    @Nonnull
    @Override
    default <S extends E> Optional<S> findOne(@Nonnull Example<S> example) {
        String key = this.getEntityRedisNamespace(example.getProbeType()) + "Method:findOne:"
                + new String(ProtoStuffSerializerUtils.serialize(example), StandardCharsets.UTF_8);
        S s = this.getRedis().get(key);
        if (s != null) {
            return Optional.of(s);
        }

        Optional<S> optional = this.getJpaRepository().findOne(example);
        optional.ifPresent(entity -> {
            this.getRedis().set(key, entity);
            this.getRedis().set(this.getEntityRedisKey(entity.getId()), entity);
        });
        return optional;
    }

    @Override
    default boolean existsById(@Nonnull I id) {
        return this.getJpaRepository().existsById(id);
    }

    @Override
    default <S extends E> boolean exists(@Nonnull Example<S> example) {
        return this.getJpaRepository().exists(example);
    }

    @Override
    default void flush() {
        this.getJpaRepository().flush();
    }

    @Nonnull
    @Override
    default <S extends E> S saveAndFlush(@Nonnull S s) {
        return this.getJpaRepository().saveAndFlush(s);
    }

    @Nonnull
    @Override
    default Optional<E> findById(@Nonnull I id) {
        String key = this.getEntityRedisKey(id);

        E obj = this.getRedis().get(key);
        if (obj != null) {
            return Optional.of(obj);
        }

        Optional<E> optional = this.getJpaRepository().findById(id);
        optional.ifPresent(s -> this.getRedis().set(key, s));
        return optional;
    }

    @Nonnull
    @Override
    default E getOne(@Nonnull I id) {
        String key = this.getEntityRedisKey(id);

        E entity = this.getRedis().get(key);
        if (entity != null) {
            return entity;
        } else {
            Optional<E> optional = this.getJpaRepository().findById(id);
            if (!optional.isPresent()) {
                throw new EntityNotFoundException();
            }
            entity = optional.get();
            this.getRedis().set(key, entity);
        }
        return entity;
    }

    @Nonnull
    @Override
    default <S extends E> S save(@Nonnull S entity) {
        entity = this.getJpaRepository().save(entity);
        this.getRedis().set(this.getEntityRedisNamespace(entity.getClass()) + entity.getId(), entity);
        return entity;
    }

}
