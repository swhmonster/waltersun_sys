package com.waltersun.lastesttech.service;

import java.util.concurrent.TimeUnit;

import com.alicp.jetcache.anno.CachePenetrationProtect;
import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;

/**
 * @author walter
 * @date 2021-06-03 11:31
 */
public interface TestService {
    /**
     * 查询测试
     *
     * @return str
     */
    String queryTest();

    /**
     * flink demo
     *
     * @return str
     */
    String flinkTest();

    /**
     * jetcache demo
     *
     * @return str
     */
    @Cached(expire = 180, cacheType = CacheType.REMOTE)
    @CacheRefresh(refresh = 60, stopRefreshAfterLastAccess = 60, timeUnit = TimeUnit.SECONDS)
    @CachePenetrationProtect
    String jetcacheTest(int condition);
    /**
     * rocketmq demo
     *
     * @return str
     */
    String rocketmqTest(int condition);
}
