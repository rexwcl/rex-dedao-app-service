package com.fullstack.gateway.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class BeanConvertUtils {
	
    /**
     * 方法说明：map转化为对象
     * 
     * @param map
     * @param t
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static <T> T mapToObject(Map<String, Object> map, Class<T> t){
        try {
            T instance = t.newInstance();
            org.apache.commons.beanutils.BeanUtils.populate(instance, map);
            return instance;
        }catch (Exception e){
            return null;
        }
    }


}
