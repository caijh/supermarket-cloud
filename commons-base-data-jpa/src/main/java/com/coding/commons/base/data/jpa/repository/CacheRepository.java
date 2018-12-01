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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import static com.coding.commons.base.data.jpa.repository.Constants.PREFIX_PAGE_LIST;
import static com.coding.commons.base.data.jpa.repository.Constants.PREFIX_PAGE_TOTAL;
import static com.coding.commons.base.data.redis.RedisUtils.LIST_EXPIRED_SECONDS;

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
        List<String> keys = all.parallelStream().map(e -> e.getClass() + ":" + e.getId())
            .collect(Collectors.toList());
        getRedisUtils().del(keys);
        return all;
    }

    @Nonnull
    @Override
    default List<E> findAll(@Nonnull Sort sort) {
        List<E> all = getJpaRepository().findAll(sort);
        List<String> keys = all.parallelStream().map(e -> e.getClass() + ":" + e.getId())
            .collect(Collectors.toList());
        getRedisUtils().del(keys);
        return all;
    }

    @Nonnull
    @Override
    default Page<E> findAll(@Nonnull Pageable pageable) {
        String key = new String(ProtoStuffSerializerUtils.serialize(pageable),
            StandardCharsets.UTF_8);
        String totalKey = PREFIX_PAGE_TOTAL + key;
        String listKey = PREFIX_PAGE_LIST + key;
        Long total = getRedisUtils().getObjectCache(totalKey, Long.class);
        if (total != null) {
            List<E> list = getRedisUtils().getListCache(listKey, getEntityClass());
            return new PageImpl<>(list, pageable, total);
        }

        Page<E> page = getJpaRepository().findAll(pageable);
        getRedisUtils()
            .setCache(totalKey, page.getTotalElements(), Long.class, LIST_EXPIRED_SECONDS);
        getRedisUtils()
            .setCache(listKey, page.getContent(), getEntityClass(), LIST_EXPIRED_SECONDS);
        return page;
    }

    @Nonnull
    @Override
    default <S extends E> List<S> findAll(@Nonnull Example<S> example) {
        List<S> entities = getJpaRepository().findAll(example);
        Class<S> probeType = example.getProbeType();
        entities.forEach(e -> getRedisUtils()
            .setCache(probeType.getSimpleName() + ":" + e.getId(), e, probeType));
        return entities;
    }

    @Nonnull
    @Override
    default <S extends E> List<S> findAll(@Nonnull Example<S> example, @Nonnull Sort sort) {
        List<S> all = getJpaRepository().findAll(example, sort);
        Class<S> probeType = example.getProbeType();
        all.forEach(e -> getRedisUtils()
            .setCache(probeType.getSimpleName() + ":" + e.getId(), e, probeType));
        return all;
    }

    @Nonnull
    @Override
    default <S extends E> Page<S> findAll(@Nonnull Example<S> example, @Nonnull Pageable pageable) {
        String key =
            new String(ProtoStuffSerializerUtils.serialize(example), StandardCharsets.UTF_8)
                + new String(ProtoStuffSerializerUtils.serialize(pageable), StandardCharsets.UTF_8);
        String totalKey = PREFIX_PAGE_TOTAL + key;
        String listKey = PREFIX_PAGE_LIST + key;
        Long total = getRedisUtils().getObjectCache(totalKey, Long.class);
        Class<S> typeArgument = example.getProbeType();
        if (total != null) {
            List<S> list = getRedisUtils().getListCache(listKey, typeArgument);
            return new PageImpl<>(list, pageable, total);
        }

        Page<S> page = getJpaRepository().findAll(example, pageable);
        getRedisUtils()
            .setCache(totalKey, page.getTotalElements(), Long.class, LIST_EXPIRED_SECONDS);
        getRedisUtils().setCache(listKey, page.getContent(), typeArgument);
        return page;
    }

    @Override
    default long count() {
        return getJpaRepository().count();
    }

    @Override
    default <S extends E> long count(@Nonnull Example<S> example) {
        return getJpaRepository().count(example);
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
            getRedisUtils().setCache(key, entity, clazz);
        });
        return list;
    }

    @Nonnull
    @Override
    default <S extends E> Optional<S> findOne(@Nonnull Example<S> example) {
        String key = new String(ProtoStuffSerializerUtils.serialize(example),
            StandardCharsets.UTF_8);
        Class<S> typeArgument = example.getProbeType();
        S cache = getRedisUtils().getObjectCache(key, typeArgument);
        if (cache != null) {
            return Optional.of(cache);
        } else {
            Optional<S> optionalS = getJpaRepository().findOne(example);
            optionalS.ifPresent(
                s -> getRedisUtils().setCache(key, s, typeArgument, LIST_EXPIRED_SECONDS));
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

        E obj = getRedisUtils().getObjectCache(key, getEntityClass());
        if (obj != null) {
            return Optional.of(obj);
        }

        Optional<E> optional = getJpaRepository().findById(id);
        optional.ifPresent(s -> getRedisUtils().setCache(key, s, getEntityClass()));
        return optional;
    }

    @Nonnull
    @Override
    default E getOne(@Nonnull I id) {
        String key = getEntityRedisKey(id);

        E obj = getRedisUtils().getObjectCache(key, getEntityClass());
        if (obj != null) {
            return obj;
        } else {
            Optional<E> optional = getJpaRepository().findById(id);
            if (!optional.isPresent()) {
                throw new EntityNotFoundException();
            }
            obj = optional.get();
            getRedisUtils().setCache(key, obj, getEntityClass());
        }
        return obj;
    }

    @Nonnull
    @Override
    default <S extends E> S save(@Nonnull S entity) {
        entity = getJpaRepository().save(entity);
        @SuppressWarnings("unchecked")
        Class<S> typeClass = (Class<S>) entity.getClass();
        getRedisUtils()
            .setCache(typeClass.getSimpleName() + ":" + entity.getId(), entity, typeClass);

        return entity;
    }

}
