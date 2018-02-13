package com.zyy.springcloud.feignconsumer.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * feign自定义配置
 *
 * @author: zhangyanyan
 * @date: 2018/2/6
 */
public class FeignConfig implements RequestInterceptor {
    private final Logger logger = LoggerFactory.getLogger(FeignConfig.class);

    @Override
    public void apply(RequestTemplate requestTemplate) {
//        requestTemplate.header("token", getHeaders(getHttpServletRequest()).get("token"));
//        requestTemplate.header("uid", getHeaders(getHttpServletRequest()).get("uid"));
    }

    private HttpServletRequest getHttpServletRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            logger.error("FeignConfig.getHttpServletRequest error:", e);
            return null;
        }
    }

    private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }
}
