package com.coding.commons.base.data.jpa.repository;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.persistence.EntityNotFoundException;

import com.coding.commons.base.PersistentObject;
import com.coding.commons.base.data.redis.ProtoStuffSerializerUtils;
import com.coding.commons.base.data.redis.RedisUtils;
import com.coding.commons.base.data.redis.Wrapper;
import com.coding.commons.util.DateUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import static java.util.stream.Collectors.toSet;

@NoRepositoryBean
public interface CacheRepository<E extends PersistentObject<I>, I extends Serializable> extends
    JpaRepository<E, I> {

    Class<E> getEntityClass();

    JpaRepository<E, I> getJpaRepository();

    RedisUtils getRedisUtils();

    default String getEntityRedisNamespace(Class<?> clazz) {
        return clazz.getSimpleName() + ":";
    }

    default String getEntityRedisKeyPattern() {
        return getEntityRedisNamespace(getEntityClass()) + "*";
    }

    default String getEntityRedisKey(I id) {
        return getEntityRedisNamespace(getEntityClass()) + id;
    }

    @Nonnull
    @Override
    default List<E> findAllById(@Nonnull Iterable<I> ids) {
        String key = getEntityRedisNamespace(getEntityClass()) + "Method:findAllById:"
            + new String(ProtoStuffSerializerUtils.serialize(new Wrapper<>(ids)), StandardCharsets.UTF_8);
        List<E> list = getRedisUtils().getList(key, getEntityClass());
        if (list != null) {
            return list;
        }
        List<E> entities = getJpaRepository().findAllById(ids);
        getRedisUtils().setList(key, entities, getEntityClass());
        return entities;
    }

    @Nonnull
    @Override
    default List<E> findAll() {
        return getJpaRepository().findAll();
    }

    @Nonnull
    @Override
    default List<E> findAll(@Nonnull Sort sort) {
        return getJpaRepository().findAll(sort);
    }

    @SuppressWarnings("unchecked")
    @Nonnull
    @Override
    default Page<E> findAll(@Nonnull Pageable pageable) {
        String key = getEntityRedisNamespace(getEntityClass()) + "Method:findAll:" + new String(ProtoStuffSerializerUtils.serialize(pageable), StandardCharsets.UTF_8);
        PageInfo<E> cache = getRedisUtils().get(key, PageInfo.class);
        if (cache != null) {
            return cache.toPage(pageable);
        }

        Page<E> page = getJpaRepository().findAll(pageable);
        getRedisUtils().set(key, PageInfo.fromPage(page), 30L);
        mappingEntityUsedInKey(page.getContent(), key);
        return page;
    }

    @Nonnull
    @Override
    default <S extends E> List<S> findAll(@Nonnull Example<S> example) {
        Class<S> probeType = example.getProbeType();
        String key = getEntityRedisNamespace(probeType) + "Method:findAll:" + new String(ProtoStuffSerializerUtils.serialize(example), StandardCharsets.UTF_8);
        List<S> list = getRedisUtils().getList(key, probeType);
        if (list != null) {
            return list;
        }

        List<S> entities = getJpaRepository().findAll(example);
        getRedisUtils().setList(key, entities, probeType);
        mappingEntityUsedInKey(entities, key);
        return entities;
    }

    @Nonnull
    @Override
    default <S extends E> List<S> findAll(@Nonnull Example<S> example, @Nonnull Sort sort) {
        Class<S> probeType = example.getProbeType();
        String key = getEntityRedisNamespace(getEntityClass()) + "Method:findAll:"
            + new String(ProtoStuffSerializerUtils.serialize(example), StandardCharsets.UTF_8)
            + new String(ProtoStuffSerializerUtils.serialize(sort), StandardCharsets.UTF_8);
        List<S> list = getRedisUtils().getList(key, probeType);
        if (list != null) {
            return list;
        }

        List<S> entities = getJpaRepository().findAll(example, sort);
        getRedisUtils().setList(key, entities, probeType);
        mappingEntityUsedInKey(entities, key);
        return entities;
    }

    @SuppressWarnings("unchecked")
    @Nonnull
    @Override
    default <S extends E> Page<S> findAll(@Nonnull Example<S> example, @Nonnull Pageable pageable) {
        String key = getEntityRedisNamespace(example.getProbeType()) + "Method:findAll:"
            + new String(ProtoStuffSerializerUtils.serialize(example), StandardCharsets.UTF_8)
            + new String(ProtoStuffSerializerUtils.serialize(pageable), StandardCharsets.UTF_8);
        PageInfo<S> cache = getRedisUtils().get(key, PageInfo.class);
        if (cache != null) {
            return cache.toPage(pageable);
        }

        Page<S> page = getJpaRepository().findAll(example, pageable);
        getRedisUtils().set(key, PageInfo.fromPage(page), 30L);
        mappingEntityUsedInKey(page.getContent(), key);
        return page;
    }

    @Override
    default long count() {
        String key = getEntityRedisNamespace(getEntityClass()) + "Method:count";
        Long cacheCount = getRedisUtils().get(key, Long.class);
        if (cacheCount != null) {
            return cacheCount;
        }
        long count = getJpaRepository().count();
        getRedisUtils().set(key, count, 15L);
        return count;
    }

    @Override
    default <S extends E> long count(@Nonnull Example<S> example) {
        String key = getEntityRedisNamespace(getEntityClass()) + "Method:count:" + new String(ProtoStuffSerializerUtils.serialize(example), StandardCharsets.UTF_8);
        Long cacheCount = getRedisUtils().get(key, Long.class);
        if (cacheCount != null) {
            return cacheCount;
        }
        long count = getJpaRepository().count(example);
        getRedisUtils().set(key, count, 15L);
        return count;
    }

    @Override
    default void deleteById(@Nonnull I id) {
        getJpaRepository().deleteById(id);
        getRedisUtils().del(getEntityRedisKey(id));
        getRedisUtils().delBatch(getEntityRedisNamespace(getEntityClass()) + "Method:*");
    }

    @Override
    default void delete(@Nonnull E entity) {
        getJpaRepository().delete(entity);
        getRedisUtils().del(getEntityRedisKey(entity.getId()));
        unMappingEntityUsedInKey(entity);
    }

    @Override
    default void deleteInBatch(@Nonnull Iterable<E> iterable) {
        getJpaRepository().deleteInBatch(iterable);
        List<String> keys = new ArrayList<>();
        List<E> entities = new ArrayList<>();
        iterable.forEach(e -> {
            keys.add(getEntityRedisKey(e.getId()));
            entities.add(e);
        });
        getRedisUtils().del(keys);
        unMappingEntityUsedInKey(entities);
    }

    @Override
    default void deleteAllInBatch() {
        getJpaRepository().deleteAllInBatch();
        getRedisUtils().delBatch(getEntityRedisKeyPattern());
    }

    @Override
    default void deleteAll(@Nonnull Iterable<? extends E> iterable) {
        getJpaRepository().deleteAll(iterable);
        List<String> keys = new ArrayList<>();
        List<E> entities = new ArrayList<>();
        iterable.forEach(e -> {
            keys.add(getEntityRedisNamespace(getEntityClass()) + e);
            entities.add(e);
        });
        getRedisUtils().del(keys);
        unMappingEntityUsedInKey(entities);
    }

    @Override
    default void deleteAll() {
        getJpaRepository().deleteAll();
        getRedisUtils().delBatch(getEntityRedisKeyPattern());
    }

    @Nonnull
    @Override
    default <S extends E> List<S> saveAll(@Nonnull Iterable<S> entities) {
        List<S> list = getJpaRepository().saveAll(entities);
        Map<String, S> map = new HashMap<>();
        list.forEach(e -> map.put(getEntityRedisNamespace(e.getClass()) + e.getId(), e));
        getRedisUtils().getRedisTemplate().opsForValue().multiSet(map);
        unMappingEntityUsedInKey(list);
        return list;
    }

    @Nonnull
    @Override
    default <S extends E> Optional<S> findOne(@Nonnull Example<S> example) {
        String key = getEntityRedisNamespace(example.getProbeType()) + "Method:findOne:"
            + new String(ProtoStuffSerializerUtils.serialize(example), StandardCharsets.UTF_8);
        S s = getRedisUtils().get(key, example.getProbeType());
        if (s != null) {
            return Optional.of(s);
        }

        Optional<S> optional = getJpaRepository().findOne(example);
        optional.ifPresent(entity -> {
            getRedisUtils().set(key, entity);
            getRedisUtils().set(getEntityRedisKey(entity.getId()), entity);
        });
        return optional;
    }

    @Override
    default boolean existsById(@Nonnull I id) {
        return getJpaRepository().existsById(id);
    }

    @Override
    default <S extends E> boolean exists(@Nonnull Example<S> example) {
        return getJpaRepository().exists(example);
    }

    @Override
    default void flush() {
        getJpaRepository().flush();
    }

    @Nonnull
    @Override
    default <S extends E> S saveAndFlush(@Nonnull S s) {
        return getJpaRepository().saveAndFlush(s);
    }

    @Nonnull
    @Override
    default Optional<E> findById(@Nonnull I id) {
        String key = getEntityRedisKey(id);

        E obj = getRedisUtils().get(key, getEntityClass());
        if (obj != null) {
            return Optional.of(obj);
        }

        Optional<E> optional = getJpaRepository().findById(id);
        optional.ifPresent(s -> getRedisUtils().set(key, s));
        return optional;
    }

    @Nonnull
    @Override
    default E getOne(@Nonnull I id) {
        String key = getEntityRedisKey(id);

        E entity = getRedisUtils().get(key, getEntityClass());
        if (entity != null) {
            return entity;
        } else {
            Optional<E> optional = getJpaRepository().findById(id);
            if (!optional.isPresent()) {
                throw new EntityNotFoundException();
            }
            entity = optional.get();
            getRedisUtils().set(key, entity);
        }
        return entity;
    }

    @Nonnull
    @Override
    default <S extends E> S save(@Nonnull S entity) {
        entity = getJpaRepository().save(entity);
        getRedisUtils().set(getEntityRedisNamespace(entity.getClass()) + entity.getId(), entity);
        unMappingEntityUsedInKey(entity);
        return entity;
    }

    default <S extends E> void mappingEntityUsedInKey(List<S> list, String refByKey) {
        if (list == null || list.isEmpty()) {
            return;
        }
        Class<? extends PersistentObject> clazz = list.get(0).getClass();
        String entityRedisNamespace = getEntityRedisNamespace(clazz);
        String prefixKey = entityRedisNamespace + "EntityRefInKey:";
        list.stream().map(e -> new Action() {
            @Override
            public String group() {
                return entityRedisNamespace;
            }

            @Override
            public void exec() {
                String key = prefixKey + e.getId();
                getRedisUtils().getRedisTemplate().expire(key, 30, TimeUnit.DAYS);
                getRedisUtils().getRedisTemplate().opsForZSet()
                               .add(key, refByKey, DateUtils.getCurrentUnixTimestamp());
            }
        }).forEachOrdered(Broker::add);
    }

    default <S extends E> void unMappingEntityUsedInKey(S entity) {
        if (entity == null) {
            return;
        }

        String entityRedisNamespace = getEntityRedisNamespace(entity.getClass());
        Broker.add(new Action() {
            @Override
            public String group() {
                return entityRedisNamespace;
            }

            @Override
            public void exec() {
                String key = entityRedisNamespace + "EntityRefInKey:" + entity.getId();
                final double PAGE_LIST_CACHE_SECONDS = 30d;
                getRedisUtils().getRedisTemplate().opsForZSet()
                               .removeRangeByScore(key, 0, DateUtils.getCurrentUnixTimestamp() - PAGE_LIST_CACHE_SECONDS);
                Set<Object> keys = getRedisUtils().getRedisTemplate().opsForZSet()
                                                  .rangeByScore(key, DateUtils
                                                      .getCurrentUnixTimestamp() - PAGE_LIST_CACHE_SECONDS, DateUtils
                                                      .getCurrentUnixTimestamp());

                if (keys != null) {
                    getRedisUtils().del(keys.parallelStream().map(Object::toString).collect(toSet()));
                }
            }
        });
    }

    default <S extends E> void unMappingEntityUsedInKey(List<S> entities) {
        if (entities == null || entities.isEmpty()) {
            return;
        }
        entities.forEach(this::unMappingEntityUsedInKey);
    }

}
