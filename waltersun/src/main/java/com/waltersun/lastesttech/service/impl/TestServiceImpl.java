package com.waltersun.lastesttech.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waltersun.lastesttech.bean.SpbtResponseEntity;
import com.waltersun.lastesttech.mapper.TestMapper;
import com.waltersun.lastesttech.service.TestService;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

/**
 * @author walter
 * @date 2021-06-03 11:31
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TestServiceImpl implements TestService {
    private final TestMapper testMapper;

    @Override
    public String queryTest() {
        return testMapper.queryTestLimit1();
    }

    @SneakyThrows
    @Override
    public String flinkTest() {
        // flink demo
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<SpbtResponseEntity> flintstones = env.fromElements(
                SpbtResponseEntity.builder().code(1).build(),
                SpbtResponseEntity.builder().code(2).build(),
                SpbtResponseEntity.builder().code(3).build());

        // 生成水印
        /*WatermarkStrategy<String> strategy = WatermarkStrategy
                .<String>forBoundedOutOfOrderness(Duration.ofSeconds(20))
                .withTimestampAssigner(new MyTimeAssigner());*/

        DataStream<SpbtResponseEntity> adults =
                flintstones.filter((FilterFunction<SpbtResponseEntity>) value -> value.getCode() > 1);
        adults.print();
        env.execute();
        return StringUtils.EMPTY;
    }

    @Override
    public String jetcacheTest(int condition) {
        return testMapper.jetcacheLimit1(condition);
    }
}
