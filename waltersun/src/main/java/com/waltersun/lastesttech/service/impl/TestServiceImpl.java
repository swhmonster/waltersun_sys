package com.waltersun.lastesttech.service.impl;

import java.time.LocalDateTime;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.waltersun.lastesttech.bean.SpbtResponseEntity;
import com.waltersun.lastesttech.kafka.KafkaProducer;
import com.waltersun.lastesttech.mapper.TestMapper;
import com.waltersun.lastesttech.rocketmq.RocketmqProducer;
import com.waltersun.lastesttech.service.SerializationService;
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
    private final KafkaProducer kafkaProducer;
    private final RocketmqProducer rocketmqProducer;
    private final Map<String, SerializationService> SerializationServiceMap;

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();


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

    @Override
    public String rocketmqTest(String topic, String msg) {
        rocketmqProducer.send(topic, msg);
        return StringUtils.EMPTY;
    }

    @Override
    public String kafkaTest(String msg) {
        kafkaProducer.send("hello world");
        return StringUtils.EMPTY;
    }

    @Override
    public byte[] SerializeTest(String type1, String type2, String str) {
        return SerializationServiceMap.get(type2).encoder(str);
    }

    @Async("asyncServiceExecutor")
    @Scheduled(fixedRate = 2000)
    @SneakyThrows
    @Override
    public void threadTest(String str) {
        threadLocal.set(str);
        Thread.sleep(6000);
        System.out.println("Spring自带的线程池："
                + Thread.currentThread().getName()
                + LocalDateTime.now()
                + "ThreadLocal："
                + threadLocal.get());
    }
}
