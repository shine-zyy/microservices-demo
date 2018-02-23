package com.zyy.springcloud.cache.autoconfigure;

import com.zyy.springcloud.cache.core.RemoteCacheBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

@Configuration
public class CacheAutoConfiguration implements ImportBeanDefinitionRegistrar, EnvironmentAware {

	ConfigProperties configProperties;

	@Override
	public void setEnvironment(Environment environment) {
		configProperties = ConfigProperties.init(environment);
	}

	@Override
	public void registerBeanDefinitions(AnnotationMetadata arg0, BeanDefinitionRegistry registry) {
		Map<String, Object> settingMap = configProperties.getSetting();
		if (settingMap != null) {
			for (Map.Entry<String, Object> entry : settingMap.entrySet()) {
				String cacheName = entry.getKey();
				Setting setting = Setting.instance(String.valueOf(entry.getValue()));

				BeanDefinitionBuilder bdb = BeanDefinitionBuilder.genericBeanDefinition(RemoteCacheBeanFactory.class);
				bdb.getBeanDefinition().setAttribute("id", cacheName);
				bdb.addPropertyValue("eviction", setting.getEviction());
				bdb.addPropertyValue("appName", configProperties.getAppName());
				bdb.addPropertyValue("cacheName", cacheName);
				registry.registerBeanDefinition(cacheName, bdb.getBeanDefinition());
			}
		}
	}

	@Configuration
	@ImportResource(value = { "classpath:/spring/redis.xml" })
	public static class RedisAutoConfiguration {

	}
}
