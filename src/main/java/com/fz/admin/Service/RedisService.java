package com.fz.admin.service;

import java.util.concurrent.TimeUnit;

public interface RedisService {
    int EXPIRE_TIME_1 = 1;

    int EXPIRE_TIME_2 = 2;

    int EXPIRE_TIME_5 = 5;

    int EXPIRE_TIME_7 = 7;

    int EXPIRE_TIME_15 = 15;

    int EXPIRE_TIME_30 = 30;

    <T> void put(String key, T obj);
    <T> void put(String key, T obj, int timeout);
    <T> void put(String key, T obj, int timeout, TimeUnit unit);

    <T> T rget(String key, Class<T> cls);

    boolean exists(String key);

    void remove(String key);

    boolean expire(String key, long timeout, TimeUnit timeUnit);
    boolean expire(String key, long timeout);

    void put(String key, String value);
    void put(String key, String value, int timeout);
    void put(String key, String value, int timeout, TimeUnit unit);

    String rget(String key);
}
