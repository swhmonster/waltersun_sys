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
     * @param topic 主题
     * @param msg   消息
     * @return str
     */
    String rocketmqTest(String topic, String msg);

    /**
     * kafka demo
     *
     * @param msg 消息
     * @return str
     */
    String kafkaTest(String msg);

    /**
     * Serialization demo
     *
     * @param type1 序列化方式
     * @param type2 序列化or反序列化
     * @param str   随机数据
     * @return str
     */
    byte[] SerializeTest(String type1, String type2, String str);

    /**
     * 线程池 结合 threadlocal 测试
     *
     * @param str 随机数据
     *            多线程测试
     */
    void threadTest(String str);

    /**
     * countDownLatchTest测试
     */
    void countDownLatchTest();

    /**
     * cycleBarriTest测试
     */
    void cycleBarriTest();

    /**
     * cycleBarriTest测试
     *
     * @return str
     */
    String aopTest();

    /**
     * synchronizedTest测试
     */
    void synchronizedTest();

    /**
     * forkJoinTest测试
     */
    void forkJoinTest();

    /**
     * redisTest测试
     *
     * @param key   键
     * @param value 键值
     */
    void redisTest(String key, String value);

    /**
     * redisStreamTest测试
     *
     * @param key   键
     * @param value 键值
     */
    void redisStreamTest(String key, String value);

    /**
     * redisLuaTest测试
     *
     * @param key   键
     * @param value 键值
     */
    void redisLuaTest(String key, String value);

    /**
     * redisTransactionTest测试
     *
     * @param key   键
     * @param value 键值
     */
    void redisTransactionTest(String key, String value);

    /**
     * redisPipeliningTest测试
     *
     * @param key   键
     * @param value 键值
     */
    void redisPipeliningTest(String key, String value);

    /**
     * LazySupplier test
     */
    void lazySupplierTest();
}
