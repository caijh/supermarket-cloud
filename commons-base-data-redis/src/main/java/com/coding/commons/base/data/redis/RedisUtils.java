package com.coding.commons.base.data.redis;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisUtils {

    public static final long LIST_EXPIRED_SECONDS = 30; // 30秒
    private static final long ENTITY_EXPIRED_SECONDS = 2530080L; // 1周

    private RedisTemplate<String, Object> redisTemplate;

    public RedisUtils(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    /**
     * get cache object.
     *
     * @param key         key
     * @param targetClass value的对象的class
     * @return T the has been cache object.
     */
    public <T> T getObjectCache(String key, Class<T> targetClass) {
        byte[] result = getRedisTemplate().execute(
            (RedisCallback<byte[]>) redisConnection -> redisConnection
                .get(key.getBytes(StandardCharsets.UTF_8)));

        if (result == null) {
            return null;
        }

        return ProtoStuffSerializerUtils.deserialize(result, targetClass);
    }

    /**
     * get the cached list.
     *
     * @param targetClazz the list element type
     */
    public <T> List<T> getListCache(String key, Class<T> targetClazz) {
        byte[] result = getRedisTemplate().execute(
            (RedisCallback<byte[]>) redisConnection -> redisConnection
                .get(key.getBytes(StandardCharsets.UTF_8)));

        if (result == null) {
            return Collections.emptyList();
        }

        return ProtoStuffSerializerUtils.deserializeList(result, targetClazz);
    }

    /**
     * cache the obj.
     *
     * @param key key
     * @param obj object to cache
     * @param <T> object class
     */
    public <T> void setCache(String key, T obj, Class<T> typeClass) {
        setCache(key, obj, typeClass, ENTITY_EXPIRED_SECONDS);
    }

    public <T> void setCache(String key, List<T> obj, Class<T> typeClass) {
        setCache(key, obj, typeClass, LIST_EXPIRED_SECONDS);
    }

    public <T> void setCache(String key, T[] array, Class<T> typClass) {
        setCache(key, new LinkedList<>(Arrays.asList(array)), typClass, LIST_EXPIRED_SECONDS);
    }

    /**
     * set cache.
     *
     * @param key            key
     * @param obj            object
     * @param expiredSeconds expired second
     * @param <T>            class type
     */
    public <T> void setCache(String key, T obj, Class<T> typeClass, Long expiredSeconds) {
        final byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        byte[] serialize = ProtoStuffSerializerUtils.serialize(obj, typeClass);
        boolean ret = Optional.ofNullable(getRedisTemplate().execute(
            (RedisCallback<Boolean>) redisConnection -> redisConnection
                .setEx(keyBytes, expiredSeconds, serialize))).orElse(true);
        if (!ret) {
            throw new RedisCacheException();
        }
    }

    public <T> void setCache(String key, List<T> list, Class<T> typeClass, Long expiredSeconds) {
        final byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        byte[] serialize = ProtoStuffSerializerUtils.serializeList(list, typeClass);
        boolean ret = Optional.ofNullable(getRedisTemplate().execute(
            (RedisCallback<Boolean>) redisConnection -> redisConnection
                .setEx(keyBytes, expiredSeconds, serialize))).orElse(true);
        if (!ret) {
            throw new RedisCacheException();
        }
    }

    public void del(String key) {
        getRedisTemplate().delete(key);
    }

    public void del(Collection<String> keys) {
        getRedisTemplate().delete(keys);
    }
}
