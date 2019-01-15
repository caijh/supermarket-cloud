package com.coding.commons.base.data.jpa.repository;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.coding.commons.base.PersistentObject;
import com.coding.commons.base.data.redis.ProtoStuffSerializerUtils;
import com.coding.commons.base.data.redis.RedisUtils;
import com.coding.commons.base.data.redis.Wrapper;
import javax.annotation.Nonnull;
import javax.persistence.EntityNotFoundException;
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

    RedisUtils getRedisUtils();

    default String getEntityRedisNamespace(Class<?> clazz) {
        return clazz.getSimpleName() + ":";
    }

    default String getEntityRedisKey(I id) {
        return getEntityRedisNamespace(getEntityClass()) + id;
    }

    default String getEntityRedisKeyPattern() {
        return getEntityRedisNamespace(getEntityClass()) + "*";
    }

    @Nonnull
    @Override
    default List<E> findAllById(@Nonnull Iterable<I> ids) {
        String key = getEntityRedisNamespace(getEntityClass()) + "Method:findAllById:" + new String(ProtoStuffSerializerUtils.serialize(new Wrapper<>(ids)), StandardCharsets.UTF_8);
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
        Page<E> cachePage = getRedisUtils().get(key, Page.class);
        if (cachePage != null) {
            return cachePage;
        }

        Page<E> page = getJpaRepository().findAll(pageable);
        getRedisUtils().set(key, page, 30L);
        return page;
    }

    @Nonnull
    @Override
    default <S extends E> List<S> findAll(@Nonnull Example<S> example) {
        String key = getEntityRedisNamespace(getEntityClass()) + "Method:findAll:" + new String(ProtoStuffSerializerUtils.serialize(example), StandardCharsets.UTF_8);
        List<S> list = getRedisUtils().getList(key, example.getProbeType());
        if (list != null) {
            return list;
        }
        List<S> entities = getJpaRepository().findAll(example);
        getRedisUtils().setList(key, entities, example.getProbeType());
        return entities;
    }

    @Nonnull
    @Override
    default <S extends E> List<S> findAll(@Nonnull Example<S> example, @Nonnull Sort sort) {
        String key = getEntityRedisNamespace(getEntityClass()) + "Method:findAll:"
            + new String(ProtoStuffSerializerUtils.serialize(example), StandardCharsets.UTF_8)
            + new String(ProtoStuffSerializerUtils.serialize(sort), StandardCharsets.UTF_8);
        List<S> list = getRedisUtils().getList(key, example.getProbeType());
        if (list != null) {
            return list;
        }
        List<S> entities = getJpaRepository().findAll(example, sort);
        getRedisUtils().setList(key, entities, example.getProbeType());
        return entities;
    }

    @SuppressWarnings("unchecked")
    @Nonnull
    @Override
    default <S extends E> Page<S> findAll(@Nonnull Example<S> example, @Nonnull Pageable pageable) {
        String key = getEntityRedisNamespace(getEntityClass()) + "Method:findAll:"
            + new String(ProtoStuffSerializerUtils.serialize(example), StandardCharsets.UTF_8)
            + new String(ProtoStuffSerializerUtils.serialize(pageable), StandardCharsets.UTF_8);
        Page cachePage = getRedisUtils().get(key, Page.class);
        if (cachePage != null) {
            return cachePage;
        }

        Page<S> page = getJpaRepository().findAll(example, pageable);
        getRedisUtils().set(key, page, 30L);
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
        getRedisUtils().set(key, count, 5L);
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
        getRedisUtils().set(key, count, 5L);
        return count;
    }

    @Override
    default void deleteById(@Nonnull I id) {
        getJpaRepository().deleteById(id);
        getRedisUtils().del(getEntityRedisKey(id));
        getRedisUtils().del(getEntityRedisNamespace(getEntityClass()) + "Method:*");
    }

    @Override
    default void delete(@Nonnull E entity) {
        getJpaRepository().delete(entity);
        getRedisUtils().del(getEntityRedisKey(entity.getId()));
        getRedisUtils().del(getEntityRedisNamespace(getEntityClass()) + "Method:*");
    }

    @Override
    default void deleteAll(@Nonnull Iterable<? extends E> iterable) {
        getJpaRepository().deleteAll(iterable);
        List<String> keys = new ArrayList<>();
        iterable.forEach(t -> keys.add(getEntityRedisNamespace(getEntityClass()) + t));
        getRedisUtils().del(keys);
        getRedisUtils().del(getEntityRedisNamespace(getEntityClass()) + "Method:*");
    }

    @Override
    default void deleteAll() {
        getJpaRepository().deleteAll();
        getRedisUtils().del(getEntityRedisKeyPattern());
        getRedisUtils().del(getEntityRedisNamespace(getEntityClass()) + "Method:*");
    }

    @Nonnull
    @Override
    default <S extends E> List<S> saveAll(@Nonnull Iterable<S> entities) {
        List<S> list = getJpaRepository().saveAll(entities);
        Map<String, S> map = new HashMap<>();
        list.forEach(e -> map.put(getEntityRedisNamespace(e.getClass()) + e.getId(), e));
        getRedisUtils().getRedisTemplate().opsForValue().multiSet(map);
        getRedisUtils().del(getEntityRedisNamespace(list.get(0).getClass()) + "Method:*");
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

    @Override
    default void deleteInBatch(@Nonnull Iterable<E> iterable) {
        getJpaRepository().deleteInBatch(iterable);
        List<String> keys = new ArrayList<>();
        iterable.forEach(t -> keys.add(getEntityRedisKey(t.getId())));
        getRedisUtils().del(keys);
        getRedisUtils().del(getEntityRedisNamespace(getEntityClass()) + "Method:*");
    }

    @Override
    default void deleteAllInBatch() {
        getJpaRepository().deleteAllInBatch();
        getRedisUtils().del(getEntityRedisKeyPattern());
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

        E obj = getRedisUtils().get(key, getEntityClass());
        if (obj != null) {
            return obj;
        } else {
            Optional<E> optional = getJpaRepository().findById(id);
            if (!optional.isPresent()) {
                throw new EntityNotFoundException();
            }
            obj = optional.get();
            getRedisUtils().set(key, obj);
        }
        return obj;
    }

    @Nonnull
    @Override
    default <S extends E> S save(@Nonnull S entity) {
        entity = getJpaRepository().save(entity);
        getRedisUtils().set(getEntityRedisNamespace(entity.getClass()) + entity.getId(), entity);
        getRedisUtils().del(getEntityRedisNamespace(entity.getClass()) + "Method:*");
        return entity;
    }

}
