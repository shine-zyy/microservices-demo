package com.zyy.springcloud.cache.core;


import com.zyy.springcloud.cache.core.common.Policy;
import com.zyy.springcloud.cache.core.redis.RedisOperCache;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

public class RemoteCacheBeanFactory extends AbstractCacheBeanFactory {

    private static Logger log = Logger.getLogger(RemoteCacheBeanFactory.class);

    @Resource(name = "redisTemplate")
    protected RedisOperations<String, Object> redisOper;

    @Resource(name = "redisTransactionTemplate")
    protected RedisOperations<String, Object> redisTxnOper;

    // 对象回收策略
    String eviction;
    Policy policy = Policy.TIME_WRITE;
    String policyPara;

    public String getEviction() {
        return eviction;
    }

    public void setEviction(String value) {
        eviction = value;
        if (StringUtils.isEmpty(value))
            return;
        if (value.indexOf(":") > -1) {
            String[] evs = value.split(":");
            this.policy = Policy.valueOf(StringUtils.trimAllWhitespace(evs[0]));
            policyPara = StringUtils.trimAllWhitespace(evs[1]);
        } else {
            this.policy = Policy.valueOf(StringUtils.trimAllWhitespace(value));
        }
    }

    public Cache getObject() throws Exception {
        if (StringUtils.isEmpty(this.getName())) {
            log.error("cache must have name!");
            return null;
        }

        // 生成cache对象
        Cache cache = remoteCacheMap.get(this.getName());
        if (cache == null) {
            RedisOperCache redisCache = new RedisOperCache();
            redisCache.setTimeOut(StringUtils.isEmpty(this.policyPara) ? 0 : Integer.valueOf(this.policyPara));
            redisCache.setCacheName(this.getName());
            redisCache.setRedisOper(redisOper);
            redisCache.setRedisTxnOper(redisTxnOper);
            cache = redisCache;
            remoteCacheMap.put(this.getName(), redisCache);
            // 预加载数据
            cache.reLoad();
        }
        return cache;
    }
}
