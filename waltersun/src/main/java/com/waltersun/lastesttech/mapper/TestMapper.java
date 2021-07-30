package com.waltersun.lastesttech.mapper;

/**
 * @author walter
 * @date 2021-06-03 11:23
 */
public interface TestMapper {
    /**
     * 查询测试
     *
     * @return str
     */
    String queryTestLimit1();

    /**
     * jetcache测试
     *
     * @param condition 条件
     * @return str
     */
    String jetcacheLimit1(int condition);
}
