package com.zyy.springcloud.common.cache;

import com.zyy.springcloud.cache.core.Cache;
import com.zyy.springcloud.cache.core.RedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DataCache {
	private static final Logger logger = LoggerFactory.getLogger(DataCache.class);
	
	@Autowired
	@Qualifier("dataCache")
    Cache dataCache;

	public RedisCache getDataCache() {
		return (RedisCache) dataCache;
	}
	public boolean setPigFarmAddresses(String name,Object value){
		
		try {
			return dataCache.set(name, value);
		} catch (Exception e) {
			logger.error("[CacheData.setGoodsAddresses]设置猪场地址缓存失败", e);
		}
		return false;
	}
	
	public boolean setData(String name,Object value){
		
		try {
			return dataCache.set(name, value);
		} catch (Exception e) {
			logger.error("[CacheData.setTradeModes]设置缓存失败", e);
		}
		return false;
	}
	
	public Object getData(String name) {
		Object data = null;
		try {
			data = dataCache.get(name);
		} catch (Exception e) {
			logger.error("[SessionCache.getPigFarmAddresses]:获取缓存信息异常", e);
		}
		return data;
	}
}

















