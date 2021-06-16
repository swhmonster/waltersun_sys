package com.waltersun.lastesttech.service.impl;

import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;

/**
 * @author walter
 * @date 2021-06-16 19:35
 */
public class MyTimeAssigner implements SerializableTimestampAssigner {

    @Override
    public long extractTimestamp(Object element, long recordTimestamp) {
        return 0;
    }
}
