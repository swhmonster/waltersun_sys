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
     * @param condition 条件（可作为cachekey）
     * @return str
     */
    @Cached(expire = 180, cacheType = CacheType.REMOTE)
    @CacheRefresh(refresh = 60, stopRefreshAfterLastAccess = 60, timeUnit = TimeUnit.SECONDS)
    @CachePenetrationProtect
    String jetcacheTest(int condition);

    /**
     * rocketmq demo
     *
     * @param msg 消息
     * @return str
     */
    String rocketmqTest(String msg);

    /**
     * kafka demo
     *
     * @param msg 消息
     * @return str
     */
    String kafkaTest(String msg);
}
