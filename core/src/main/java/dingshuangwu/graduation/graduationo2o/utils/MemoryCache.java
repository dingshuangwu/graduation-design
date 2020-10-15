package dingshuangwu.graduation.graduationo2o.utils;


import avro.shaded.com.google.common.collect.Maps;
import dingshuangwu.graduation.graduationo2o.pojo.CacheObject;

import java.util.Map;

/**
 * @author dingshuangwu
 * @date 2019-12-24
 */
public class MemoryCache {
    public static Map<String, CacheObject> cacheObjectMap = Maps.newConcurrentMap();
    private static final long cachedTime = 30 * 60000;

    public static Object get(String key) {
        if (cacheObjectMap.containsKey(key)) {
            CacheObject cacheObject = cacheObjectMap.get(key);
            if (cacheObject != null) {
                if (System.currentTimeMillis() - cacheObject.getCreateTime() < cachedTime) {
                    cacheObject.setCreateTime(System.currentTimeMillis());
                    return cacheObject.getData();
                } else {
                    cacheObjectMap.remove(key);
                }
            }
        }
        return null;
    }

    public static void setLastConnectTime(String key) {
        if (cacheObjectMap.containsKey(key)) {
            CacheObject cacheObject = cacheObjectMap.get(key);
            if (cacheObject != null) {
                cacheObject.setCreateTime(System.currentTimeMillis());
            }
        }
    }

    public static void put(String key, Object value) {
        CacheObject cacheObject = new CacheObject(value);
        cacheObjectMap.put(key, cacheObject);
    }

    public static void remove(String key) {
        CacheObject cacheObject = cacheObjectMap.get(key);
        if (cacheObject != null) {
            cacheObjectMap.remove(key);
        }
    }
}
