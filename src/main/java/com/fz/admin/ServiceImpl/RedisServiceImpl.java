package com.fz.admin.serviceImpl;

import com.fz.admin.service.RedisService;
import com.fz.admin.core.JsonCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service("redisServiceImpl")
public class RedisServiceImpl implements RedisService {

    private StringRedisTemplate redisTemplate;

    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public <T> void put(String key, T obj) {
        redisTemplate.opsForValue().set(key,JsonCore.toJson(obj));
    }

    public <T> void put(String key, T obj, int timeout) {
        put(key, obj, timeout, TimeUnit.MINUTES);
    }

    public <T> void put(String key, T obj, int timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, JsonCore.toJson(obj), timeout, unit);
    }

    public <T> T rget(String key, Class<T> cls) {
        return JsonCore.fromJson(redisTemplate.opsForValue().get(key),cls);
    }

    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    public void remove(String key) {
        redisTemplate.delete(key);
    }

    public boolean expire(String key, long timeout, TimeUnit timeUnit) {
        return redisTemplate.expire(key, timeout, timeUnit);
    }

    public boolean expire(String key, long timeout) {
        return expire(key, timeout, TimeUnit.MINUTES);
    }

    public void put(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void put(String key, String value, int timeout) {
        put(key, value, timeout, TimeUnit.MINUTES);
    }

    public void put(String key, String value, int timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }


    public String rget(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}