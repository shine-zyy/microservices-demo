package com.zyy.springcloud.common.util;

import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;


/**
 * @author A18ccms a18ccms_gmail_com
 * @ClassName: ReflectUtil
 * @Description: 反射工具类
 * @date 2017年5月16日 下午3:50:36
 */
public class ReflectUtil {

    /**
     * 反射根据属性名的字符串返回值
     *
     * @param obj
     * @param field
     * @return
     * @throws Exception
     */
    public static Object getValueByCoulmn(Object obj, String field) throws Exception {
        Class<?> clazz = obj.getClass();
        Field field1;
        try {
            field1 = clazz.getDeclaredField(field);
            PropertyDescriptor pd = new PropertyDescriptor(field1.getName(), clazz);
            Method getMethod = pd.getReadMethod();
            Object o = getMethod.invoke(obj);
            return (o == null) ? "" : o;
        } catch (Exception e) {
            throw e;
        }

    }

    /**
     * 判断bean中属性值是不是空值
     *
     * @param bean
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static boolean emptyBean(Object bean) throws Exception {
        Method[] methods = bean.getClass().getMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            if (method.getName().startsWith("get") && !method.getName().equals("getClass")) {
                Object value = method.invoke(bean);
                if (value != null) {
                    if (value instanceof List) {
                        List list = (List) value;
                        if (list.size() > 0) {
                            return false;
                        }
                    } else if (value instanceof String) {
                        if (!StringUtils.isEmpty(value.toString())) {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
