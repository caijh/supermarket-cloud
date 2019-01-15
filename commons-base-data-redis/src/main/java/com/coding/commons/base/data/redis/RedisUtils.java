package com.coding.commons.base.data.redis;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisUtils {

    private static final long LIST_EXPIRED_SECONDS = 30; // 30秒
    private static final long ENTITY_EXPIRED_SECONDS = 2530080L; // 1周

    private RedisTemplate<String, Object> redisTemplate;

    public RedisUtils(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public <T> void set(String key, T obj) {
        set(key, obj, ENTITY_EXPIRED_SECONDS);
    }

    @SuppressWarnings("unchecked")
    public <T> void set(String key, T obj, Long expire) {
        Class<T> clazz = (Class<T>) obj.getClass();
        if (clazz.isArray() || Collection.class.isAssignableFrom(clazz) || Map.class.isAssignableFrom(clazz)) {
            Wrapper<T> wrapper = new Wrapper<>(obj);
            set(key, wrapper, Wrapper.class, expire);
        } else {
            set(key, obj, clazz, expire);
        }
    }

    private <T> void set(String key, T obj, Class<T> typeClass, Long expire) {
        final byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        byte[] serialize = ProtoStuffSerializerUtils.serialize(obj, typeClass);
        setEx(keyBytes, serialize, expire);
    }

    public <T> void setList(String key, List<T> list, Class<T> clazz) {
        setList(key, list, clazz, LIST_EXPIRED_SECONDS);
    }

    public <T> void setList(String key, List<T> list, Class<T> clazz, Long expire) {
        final byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        byte[] serialize = ProtoStuffSerializerUtils.serializeList(list, clazz);
        setEx(keyBytes, serialize, expire);
    }

    private void setEx(byte[] keyBytes, byte[] serializeValue, Long expire) {
        boolean ret = Optional.ofNullable(getRedisTemplate().execute((RedisCallback<Boolean>) redisConnection -> redisConnection.setEx(keyBytes, expire, serializeValue))).orElse(true);
        if (!ret) {
            throw new RedisCacheException();
        }
    }

    /**
     * get cache object.
     *
     * @param key         key
     * @param targetClass value的对象的class
     * @return T the has been cache object.
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> clazz) {
        byte[] result = getRedisTemplate().execute((RedisCallback<byte[]>) redisConnection -> redisConnection.get(key.getBytes(StandardCharsets.UTF_8)));
        if (result == null) {
            return null;
        }

        if (clazz.isArray() || Collection.class.isAssignableFrom(clazz) || Map.class.isAssignableFrom(clazz)) {
            return (T) ProtoStuffSerializerUtils.deserialize(result, Wrapper.class).getData();
        }

        return ProtoStuffSerializerUtils.deserialize(result, clazz);
    }

    /**
     * get the cached list.
     *
     * @param clazz the list element type
     */
    public <T> List<T> getList(String key, Class<T> clazz) {
        byte[] result = getRedisTemplate().execute((RedisCallback<byte[]>) redisConnection -> redisConnection.get(key.getBytes(StandardCharsets.UTF_8)));

        if (result == null) {
            return Collections.emptyList();
        }

        return ProtoStuffSerializerUtils.deserializeList(result, clazz);
    }

    public void del(String key) {
        getRedisTemplate().delete(key);
    }

    public void del(Collection<String> keys) {
        getRedisTemplate().delete(keys);
    }
}
