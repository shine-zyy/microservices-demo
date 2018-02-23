package com.zyy.springcloud.common.cache;

import com.zyy.springcloud.cache.core.Cache;
import com.zyy.springcloud.cache.core.RedisCache;
import com.zyy.springcloud.common.constant.CommonConstant;
import com.zyy.springcloud.common.util.ContextHolderUtil;
import com.zyy.springcloud.common.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Component
public class RUserCache {
    private static final Logger logger = LoggerFactory.getLogger(RUserCache.class);

    @Autowired
    @Qualifier("userCache")
    Cache userCache;

    RedisCache rUserCache;

    @PostConstruct
    void init() {
        this.rUserCache = (RedisCache) userCache;
    }

    /**
     * set user data to cache with key(token)
     *
     * @param userModel
     * @return
     */
    public boolean setUserInfo(String token, Object userModel) {
        try {
            return rUserCache.set(token, userModel);
        } catch (Exception e) {
            logger.error("[rUserCache.setUserInfo]设置缓存失败", e);
        }
        return false;
    }

    /**
     * set user data to cache and add RSESSIONID to cookie with key(token)
     *
     * @param userModel
     * @return
     */
    public boolean setUserInfo(Object userModel) {
        String token = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        if (setUserInfo(token, userModel)) { //设置cookie成功
            SessionUtil.setCookie(ContextHolderUtil.getResponse(), CommonConstant.RSESSIONID_NAME, token);
            return true;
        }
        return false;
    }

    /**
     * get user data from cache with key(token)
     *
     * @param token
     * @return
     */
    public Object getUserInfo(String token) {
        Object userInfo = null;
        try {
            userInfo = rUserCache.get(token);
        } catch (Exception e) {
            logger.error("[SessionCache.getUserInfo]:获取用户缓存信息异常", e);
        }
        return userInfo;
    }

    /**
     * get token from RSESSIONID by cookie and get user data from cache with key(token)
     *
     * @param request
     * @return
     */
    public Object getUserInfo(HttpServletRequest request) {
        String token = SessionUtil.getRSessionId(request);
        return getUserInfo(token);
    }

    /**
     * get token from http header and get user data from cache with key(token)
     *
     * @return
     */
    public Object getUserInfo() {
        HttpServletRequest request = ContextHolderUtil.getRequest();
        if (null != request) {
            return getUserInfo(request.getHeader(CommonConstant.HTTP_HEADER_TOKEN));
        }
        return null;
    }
}
