package com.coding.commons.base.data.redis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class RedisUtilsTest {

    private RedisUtils redisUtils;

    @Before
    public void setUp() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory("127.0.0.1", 6379);
        connectionFactory.afterPropertiesSet();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.afterPropertiesSet();
        redisUtils = new RedisUtils(redisTemplate);
    }

    @After
    public void tearDown() throws Exception {
        redisUtils = null;
    }

    @Test
    public void test() {
        redisUtils.set("integer", 1);

        Integer integer = redisUtils.get("integer", Integer.class);
        Assert.assertEquals(1, (int) integer);

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        redisUtils.set("list", list);
        List list1 = redisUtils.get("list", list.getClass());
        Assert.assertEquals("a", list1.get(0));

        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        redisUtils.set("map", map);
        Map map1 = redisUtils.get("map", Map.class);
        Assert.assertEquals(map.size(), map1.size());
        Assert.assertEquals(1, map1.get("a"));
        Assert.assertEquals(2, map1.get("b"));

        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        redisUtils.setList("list2", list2, Integer.class);
        List<Integer> list21 = redisUtils.getList("list2", Integer.class);
        Assert.assertEquals(list21.size(), list2.size());
        Assert.assertEquals(list21.get(0), list2.get(0));

        redisUtils.del("integer");
        redisUtils.del(Arrays.asList("list", "map", "list2"));

        redisUtils.set("test", "test", 0L);
        redisUtils.del("test");

        redisUtils.set("u:1", 1);
        redisUtils.set("u:2", 2);
        redisUtils.delBatch("*");
        Set<String> keys = redisUtils.getRedisTemplate().keys("*");
        Assert.assertTrue(keys == null || keys.isEmpty());
    }

}
