package com.zyy.springcloud.common.util;

import com.zyy.springcloud.common.constant.CommonConstant;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionUtil {

    public static HttpSession getSession(HttpServletRequest request) {
        return request.getSession(true);
    }

    public static void setAttribute(HttpServletRequest request, String name, Object value) {
        getSession(request).setAttribute(name, value);
    }

    public static Object getAttribute(HttpServletRequest request, String name) {
        return getSession(request).getAttribute(name);
    }


    public static void removeAtrribute(HttpServletRequest request, String name) {
        getSession(request).removeAttribute(name);
    }

    public static String getValueByCookie(String name, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (null == cookies || null == name || name.length() == 0) {
            return "";
        }
        if (cookies != null && name != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (name.equals(cookies[i].getName())) {
                    return cookies[i].getValue();
                }
            }
        }
        return "";
    }

    public static String getJSessionId(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            String jsessionid = "";
            for (int i = 0; i < cookies.length; i++) {
                if ("JSESSIONID".equals(cookies[i].getName())) {
                    jsessionid = cookies[i].getValue();
                    return jsessionid;
                }
            }
        }
        return null;
    }

    public static String getRSessionId(HttpServletRequest request) {
        if (request != null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                String rSessionId = "";
                for (int i = 0; i < cookies.length; i++) {
                    if (CommonConstant.RSESSIONID_NAME.equals(cookies[i].getName())) {
                        rSessionId = cookies[i].getValue();
                        return rSessionId;
                    }
                }
            }
        }

        return null;
    }

    public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        if (null == name) {
            return;
        }
        Cookie cookie = getCookie(request, name);
        if (null != cookie) {
            cookie.setPath("/");
            cookie.setValue("");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }

    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (null == cookies || null == name || name.length() == 0) {
            return null;
        }
        Cookie cookie = null;
        for (Cookie c : cookies) {
            if (name.equals(c.getName())) {
                cookie = c;
                break;
            }
        }
        return cookie;
    }

    public static void setCookie(HttpServletResponse response, String name, String value) {
        setCookie(response, name, value, CommonConstant.COOKIE_MAX_AGE);
    }

    public static void setCookie(HttpServletResponse response, String name, String value, int maxValue) {
        if (null == name) {
            return;
        }
        if (null == value) {
            value = "";
        }
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (maxValue != 0) {
            cookie.setMaxAge(maxValue);
        } else {
            cookie.setMaxAge(CommonConstant.COOKIE_MAX_AGE);
        }
        response.addCookie(cookie);
    }
}
