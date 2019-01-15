package com.coding.commons.base.data.jpa.repository;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.persistence.EntityNotFoundException;

import com.coding.commons.base.PersistentObject;
import com.coding.commons.base.data.redis.ProtoStuffSerializerUtils;
import com.coding.commons.base.data.redis.RedisUtils;
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

    default String getEntityRedisKey(I id) {
        return getEntityClass().getSimpleName() + ":" + id;
    }

    default String getEntityRedisKeyPattern() {
        return getEntityClass().getSimpleName() + ":" + "*";
    }

    @Nonnull
    @Override
    default List<E> findAllById(@Nonnull Iterable<I> ids) {
        List<E> list = new ArrayList<>();
        ids.forEach(id -> findById(id).ifPresent(list::add));
        return list;
    }

    @Nonnull
    @Override
    default List<E> findAll() {
        List<E> all = getJpaRepository().findAll();
        List<String> keys = all.parallelStream().map(e -> e.getClass() + ":" + e.getId()).collect(Collectors.toList());
        getRedisUtils().del(keys);
        return all;
    }

    @Nonnull
    @Override
    default List<E> findAll(@Nonnull Sort sort) {
        List<E> all = getJpaRepository().findAll(sort);
        List<String> keys = all.parallelStream().map(e -> e.getClass() + ":" + e.getId()).collect(Collectors.toList());
        getRedisUtils().del(keys);
        return all;
    }

    @SuppressWarnings("unchecked")
    @Nonnull
    @Override
    default Page<E> findAll(@Nonnull Pageable pageable) {
        String key = getEntityClass().getSimpleName() + ":page:" + new String(ProtoStuffSerializerUtils.serialize(pageable), StandardCharsets.UTF_8);
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
        List<S> entities = getJpaRepository().findAll(example);
        Class<S> probeType = example.getProbeType();
        entities.forEach(e -> getRedisUtils().set(probeType.getSimpleName() + ":" + e.getId(), e));
        return entities;
    }

    @Nonnull
    @Override
    default <S extends E> List<S> findAll(@Nonnull Example<S> example, @Nonnull Sort sort) {
        List<S> all = getJpaRepository().findAll(example, sort);
        Class<S> probeType = example.getProbeType();
        all.forEach(e -> getRedisUtils().set(probeType.getSimpleName() + ":" + e.getId(), e));
        return all;
    }

    @SuppressWarnings("unchecked")
    @Nonnull
    @Override
    default <S extends E> Page<S> findAll(@Nonnull Example<S> example, @Nonnull Pageable pageable) {
        String key = getEntityClass().getSimpleName() + ":page:"
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
        String key = getEntityClass().getSimpleName() + ":count";
        Integer cacheCount = getRedisUtils().get(key, Integer.class);
        if (cacheCount != null) {
            return cacheCount;
        }
        long count = getJpaRepository().count();
        getRedisUtils().set(key, count, 5L);
        return count;
    }

    @Override
    default <S extends E> long count(@Nonnull Example<S> example) {
        String key = getEntityClass().getSimpleName() + ":count:" + new String(ProtoStuffSerializerUtils.serialize(example), StandardCharsets.UTF_8);
        Integer cacheCount = getRedisUtils().get(key, Integer.class);
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
    }

    @Override
    default void delete(@Nonnull E entity) {
        getJpaRepository().delete(entity);
        getRedisUtils().del(getEntityRedisKey(entity.getId()));
    }

    @Override
    default void deleteAll(@Nonnull Iterable<? extends E> iterable) {
        getJpaRepository().deleteAll(iterable);
        List<String> keys = new ArrayList<>();
        iterable.forEach(t -> keys.add(t.getClass().getSimpleName() + ":" + t.getId()));
        getRedisUtils().del(keys);
    }

    @Override
    default void deleteAll() {
        getJpaRepository().deleteAll();
        getRedisUtils().del(getEntityRedisKeyPattern());
    }

    @SuppressWarnings("unchecked")
    @Nonnull
    @Override
    default <S extends E> List<S> saveAll(@Nonnull Iterable<S> entities) {
        List<S> list = getJpaRepository().saveAll(entities);
        list.forEach(entity -> {
            Class<S> clazz = (Class<S>) entity.getClass();
            String key = clazz.getSimpleName() + ":" + entity.getId();
            getRedisUtils().set(key, entity);
        });
        return list;
    }

    @Nonnull
    @Override
    default <S extends E> Optional<S> findOne(@Nonnull Example<S> example) {
        String key = getEntityClass().getSimpleName() + ":findOne:"
            + new String(ProtoStuffSerializerUtils.serialize(example), StandardCharsets.UTF_8);
        Class<S> typeArgument = example.getProbeType();
        S cache = getRedisUtils().get(key, typeArgument);
        if (cache != null) {
            return Optional.of(cache);
        } else {
            Optional<S> optionalS = getJpaRepository().findOne(example);
            optionalS.ifPresent(s -> getRedisUtils().set(key, s));
            return optionalS;
        }
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
    }

    @Override
    default void deleteAllInBatch() {
        List<E> list = getJpaRepository().findAll();
        getJpaRepository().deleteAllInBatch();
        getRedisUtils()
            .del(list.stream().map(t -> getEntityRedisKey(t.getId())).collect(Collectors.toList()));
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
        getRedisUtils().set(entity.getClass().getSimpleName() + ":" + entity.getId(), entity);
        return entity;
    }

}
