package com.zyy.springcloud.cache.core.redis;


import com.zyy.springcloud.cache.core.RedisCache;
import com.zyy.springcloud.cache.core.load.LoadHandler;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisOperCache implements RedisCache {

    private ValueOperations<String, Object> valueOper;
    private RedisOperations<String, Object> redisOper;
    private RedisOperations<String, Object> redisTxnOper;
    private HashOperations<String, String, Object> hashOper;

    private Integer timeOut;

    private String cacheName;

    public void setRedisTxnOper(RedisOperations<String, Object> redisTxnOper) {
        this.redisTxnOper = redisTxnOper;
    }

    public void setRedisOper(RedisOperations<String, Object> redisOper) {
        this.redisOper = redisOper;
        this.valueOper = redisOper.opsForValue();
        this.hashOper = redisOper.opsForHash();
    }

    public void setValueOper(ValueOperations<String, Object> valueOper) {
        this.valueOper = valueOper;
    }

    public void setHashOper(HashOperations<String, String, Object> hashOper) {
        this.hashOper = hashOper;
    }

    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    @Override
    public String getCacheName() {
        return cacheName;
    }

    private String keyName(String key) {
        StringBuffer str = new StringBuffer();
        str.append(this.cacheName).append(":").append(key);
        return (String) str.toString();
    }

    @Override
    public boolean set(String key, Object value) throws Exception {
        if (timeOut > 0) {
            valueOper.set(keyName(key), value, timeOut, TimeUnit.SECONDS);
        } else {
            valueOper.set(keyName(key), value);
        }
        return Boolean.TRUE;
    }

    @Override
    public boolean set(String key, Object value, long second) throws Exception {
        valueOper.set(keyName(key), value, second, TimeUnit.SECONDS);
        return Boolean.TRUE;
    }

    @Override
    public Object get(String key) throws Exception {
        return valueOper.get(keyName(key));
    }

    @Override
    public boolean remove(String key) throws Exception {
        redisOper.delete(keyName(key));
        return Boolean.TRUE;
    }

    @Override
    public void setLoadHandler(LoadHandler loadHandler) {
        throw new UnsupportedOperationException("redis is unsupported !");
    }

    @Override
    public LoadHandler getloadHandler() {
        throw new UnsupportedOperationException("redis is unsupported !");
    }

    @Override
    public List<?> getKeys() {
        throw new UnsupportedOperationException("redis is unsupported !");
    }

    @Override
    public long getCount() {
        throw new UnsupportedOperationException("redis is unsupported !");
    }

    @Override
    public long getHitCount() {
        throw new UnsupportedOperationException("redis is unsupported !");
    }

    @Override
    public boolean reLoad() {
        return true;
        //throw new  java.lang.UnsupportedOperationException("redis is unsupported !");
    }

    @Override
    public boolean removeAll(Collection<String> key) {
        //throw new  java.lang.UnsupportedOperationException("redis is unsupported !");
        redisOper.delete(key);
        return true;
    }

    @Override
    public boolean removeAll() {
        throw new UnsupportedOperationException("redis is unsupported !");
    }

    @Override
    public long increment(String key, long delta) {
        return valueOper.increment(key, delta);
    }

    @Override
    public Double increment(String key, double delta) {
        return valueOper.increment(key, delta);
    }

    @Override
    public Boolean setIfAbsent(String key, Object value) {
        return valueOper.setIfAbsent(key, value);
    }

    @Override
    public Boolean setIfAbsentWithCacheName(String key, Object value) {
        return valueOper.setIfAbsent(keyName(key), value);
    }

    @Override
    public Object getAndSet(String key, Object value) {
        return valueOper.getAndSet(key, value);
    }

    @Override
    public void multiSet(Map<String, Object> m) {
        valueOper.multiSet(m);
    }

    @Override
    public Boolean multiSetIfAbsent(Map<String, Object> m) {
        return valueOper.multiSetIfAbsent(m);
    }

    @Override
    public List<Object> multiGet(Collection<String> keys) {
        return valueOper.multiGet(keys);
    }

    @Override
    public void watch(String keys) {
        redisTxnOper.watch(keys);
    }

    @Override
    public void watch(Collection<String> keys) {
        redisTxnOper.watch(keys);
    }

    @Override
    public void unwatch() {
        redisTxnOper.unwatch();
    }

    @Override
    public void multi() {
        redisTxnOper.multi();
    }

    @Override
    public List<Object> exec() {
        return redisTxnOper.exec();
    }

    @Override
    public boolean hSet(String key, String field, Object value) {
        hashOper.put(key, field, value);
        return true;
    }

    @Override
    public boolean hMSet(String key, Map<String, Object> m) {
        hashOper.putAll(key, m);
        return true;
    }

    @Override
    public boolean hMSetIfAbsent(String key, String field, Object value) {
        return hashOper.putIfAbsent(key, field, value);
    }

    @Override
    public Object hGet(String key, String field) {
        return hashOper.get(key, field);
    }

    @Override
    public Map<String, Object> hGetAll(String key) {
        return hashOper.entries(key);
    }

    @Override
    public List<Object> hMGet(String key, Collection<String> fields) {
        return hashOper.multiGet(key, fields);
    }

    @Override
    public boolean hDel(String key, Object... fields) {
        hashOper.delete(key, fields);
        return true;
    }

    @Override
    public boolean hExists(String key, String field) {
        return hashOper.hasKey(key, field);
    }

    @Override
    public Set<String> hKeys(String key) {
        return hashOper.keys(key);
    }

    @Override
    public List<Object> hVals(String key) {
        return hashOper.values(key);
    }

}