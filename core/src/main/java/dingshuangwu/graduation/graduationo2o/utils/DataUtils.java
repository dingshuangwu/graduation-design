package dingshuangwu.graduation.graduationo2o.utils;

import dingshuangwu.graduation.graduationo2o.pojo.CacheUserInfoVO;
import dingshuangwu.graduation.graduationo2o.pojo.JurisdictionVO;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class DataUtils {
    public static JurisdictionVO getUserJurisdiction() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String name = request.getHeader("name");
        CacheUserInfoVO cacheUserInfo = (CacheUserInfoVO) MemoryCache.get("userInfo" + name);
        return cacheUserInfo.getJurisdiction();
    }

    public static CacheUserInfoVO getUserCacheUserInfo() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String name = request.getHeader("name");
        CacheUserInfoVO cacheUserInfo = (CacheUserInfoVO) MemoryCache.get("userInfo" + name);
        return cacheUserInfo;
    }

    public static JurisdictionVO getSomeUserJurisdictionBy(String name) {
        CacheUserInfoVO cacheUserInfo = (CacheUserInfoVO) MemoryCache.get("userInfo" + name);
        return cacheUserInfo.getJurisdiction();
    }

    public static CacheUserInfoVO getSomeUserCacheUserInfoBy(String name) {
        CacheUserInfoVO cacheUserInfo = (CacheUserInfoVO) MemoryCache.get("userInfo" + name);
        return cacheUserInfo;
    }

    public static void removeCacheUserInfo() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String name = request.getHeader("name");
        MemoryCache.remove("userInfo" + name);
    }

    public static void removeCacheUserInfoByName(String name) {
        MemoryCache.remove("userInfo" + name);
    }

    public static void setCacheUserInfoByName(String name, CacheUserInfoVO data) {
        MemoryCache.put("userInfo" + name, data);
    }

    public static void refreshCacheUserInfoAliveDateByName(String name) {
        MemoryCache.setLastConnectTime("userInfo" + name);
    }
}
