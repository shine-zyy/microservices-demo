package com.zyy.springcloud.cache.core;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RemoteCache extends Cache {
    /**
     * 设置key对应数据,过期时间second秒
     *
     * @param key
     * @param value
     * @param second
     * @return
     * @throws Exception
     */
    boolean set(String key, Object value, long second) throws Exception;

    /**
     * 删除所有key对应value
     *
     * @param key
     * @return
     * @see {@RedisOperations.delete}
     */
    boolean removeAll(Collection<String> key);

    /**
     * 增/减key对应的值,原子操作
     *
     * @param key
     * @param delta 增量,整数
     * @return
     * @see {@ValueOperations}
     */
    long increment(String key, long delta);

    /**
     * 增/减key对应的整形值,原子操作
     *
     * @param key
     * @param delta 增量,小数
     * @return
     * @see {@ValueOperations}
     */
    Double increment(String key, double delta);

    /**
     * 如果key不存在则设置key
     *
     * @param key
     * @param value
     * @return 成功/失败
     * @see {@ValueOperations}
     */
    Boolean setIfAbsent(String key, Object value);

    /**
     * 如果key不存在则设置key
     * key=keyName(key)
     *
     * @param key
     * @param value
     * @return 成功/失败
     * @see {@ValueOperations}
     */
    public Boolean setIfAbsentWithCacheName(String key, Object value);

    /**
     * 设置key对应的值，并返回old value
     *
     * @param key
     * @param value
     * @return
     * @see {@ValueOperations}
     */
    Object getAndSet(String key, Object value);

    /**
     * Set multiple keys to multiple values using key-value pairs provided in tuple
     *
     * @param m
     * @see {@ValueOperations}
     */
    void multiSet(Map<String, Object> m);

    /**
     * Set multiple keys to multiple values using key-value pairs
     * only if the provided key does not exist
     *
     * @param m
     * @return
     * @see {@ValueOperations}
     */
    Boolean multiSetIfAbsent(Map<String, Object> m);

    /**
     * Get multiple keys
     *
     * @param keys
     * @return
     * @see {@ValueOperations}
     */
    List<Object> multiGet(Collection<String> keys);

    /**
     * 对单个key进行watch，用于事务
     * Watch given key for modifications during transaction started
     *
     * @param keys
     * @see {@RedisOperations}
     */
    void watch(String keys);

    /**
     * 对多个key进行watch，用于事务
     * Watch given keys for modifications during transaction started
     *
     * @param keys
     * @see {@RedisOperations}
     */
    void watch(Collection<String> keys);

    /**
     * 取消之前所有的watch操作
     * Flushes all the previously watch keys
     *
     * @see {@RedisOperations}
     */
    void unwatch();

    /**
     * 标记事务开始
     * Mark the start of a transaction block
     *
     * @see {@RedisOperations}
     */
    void multi();

    /**
     * 执行事务
     * Executes all queued commands in a transaction started with RedisOperations.multi()
     *
     * @return 事务执行结果
     * @see {@RedisOperations}
     */
    List<Object> exec();

    /**
     * hash set操作
     * Set the value of a hash field
     *
     * @param key
     * @param field
     * @param value
     * @return
     * @see {@HashOperations}
     */
    boolean hSet(String key, String field, Object value);

    /**
     * Set multiple hash fields to multiple values using data provided in hashes
     *
     * @param key
     * @param m
     * @return
     * @see {@HashOperations}
     */
    boolean hMSet(String key, Map<String, Object> m);

    /**
     * Set the value of a hash field only if field does not exist
     *
     * @param key
     * @param field
     * @param value
     * @return
     * @see {@HashOperations}
     */
    boolean hMSetIfAbsent(String key, String field, Object value);

    /**
     * Get value for given field from hash at key.
     *
     * @param key
     * @param field
     * @return
     * @see {@HashOperations}
     */
    Object hGet(String key, String field);

    /**
     * Get entire hash stored at key.
     *
     * @param key
     * @return
     * @see {@HashOperations}
     */
    Map<String, Object> hGetAll(String key);

    /**
     * Get values for given fields from hash at key.
     *
     * @param key
     * @param fields
     * @return
     * @see {@HashOperations}
     */
    List<Object> hMGet(String key, Collection<String> fields);

    /**
     * Delete given hash fields.
     *
     * @param key
     * @param fields
     * @return
     * @see {@HashOperations}
     */
    boolean hDel(String key, Object... fields);

    /**
     * Determine if given hash field exists.
     *
     * @param key
     * @param field
     * @return
     * @see {@HashOperations}
     */
    boolean hExists(String key, String field);

    /**
     * Get key set (fields) of hash at key.
     *
     * @param key
     * @return
     * @see {@HashOperations}
     */
    Set<String> hKeys(String key);

    /**
     * Get entry set (values) of hash at field.
     *
     * @param key
     * @return
     * @see {@HashOperations}
     */
    List<Object> hVals(String key);
}
