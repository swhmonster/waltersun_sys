package com.waltersun.lastesttech.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scripting.ScriptSource;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.waltersun.lastesttech.annotation.MyAnnotation;
import com.waltersun.lastesttech.bean.RedisLuaBean;
import com.waltersun.lastesttech.bean.SpbtResponseEntity;
import com.waltersun.lastesttech.enums.SeperateConstant;
import com.waltersun.lastesttech.kafka.KafkaProducer;
import com.waltersun.lastesttech.mapper.TestMapper;
import com.waltersun.lastesttech.rocketmq.RocketmqProducer;
import com.waltersun.lastesttech.service.SerializationService;
import com.waltersun.lastesttech.service.TestService;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author walter
 * @date 2021-06-03 11:31
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TestServiceImpl implements TestService {


    private final TestMapper testMapper;
    private final RedisTemplate redisTemplate;
    private final KafkaProducer kafkaProducer;
    private final RocketmqProducer rocketmqProducer;
    private final Map<String, SerializationService> SerializationServiceMap;

    @Resource(name = "asyncServiceExecutor")
    private Executor asyncServiceExecutor;
    @Resource(name = "forkJoinPool")
    private ForkJoinPool forkJoinPool;

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();
    private static final CountDownLatch countDownLatch = new CountDownLatch(2);
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(2);


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

    @SneakyThrows
    @Override
    public void countDownLatchTest() {
        log.debug("-----wait all children stop-----");

        // thread1
        asyncServiceExecutor.execute(() -> {
            try {
                log.debug("thread1 start");
                // 推荐用TimeUnit替代Thread.sleep
                TimeUnit.MILLISECONDS.sleep(2);
            } catch (Exception e) {
                log.error("thread1 exception", e);
            } finally {
                countDownLatch.countDown();
            }
            log.debug("thread1 over");
        });

        // thread2
        asyncServiceExecutor.execute(() -> {
            try {
                log.debug("thread2 start");
                TimeUnit.MILLISECONDS.sleep(3);
            } catch (Exception e) {
                log.error("thread2 exception", e);
            } finally {
                countDownLatch.countDown();
            }
            log.debug("thread2 over");
        });

        countDownLatch.await();
        log.debug("-----all children is stoped-----");
    }

    @Override
    public void cycleBarriTest() {
        // thread1
        asyncServiceExecutor.execute(() -> {
            try {
                log.debug("thread1 step1");
                cyclicBarrier.await();

                log.debug("thread1 step2");
                cyclicBarrier.await();

                log.debug("thread1 step3");
            } catch (Exception e) {
                log.error("thread1 exception", e);
            }
        });

        // thread2
        asyncServiceExecutor.execute(() -> {
            try {
                log.debug("thread2 step1");
                cyclicBarrier.await();

                log.debug("thread2 step2");
                cyclicBarrier.await();

                log.debug("thread2 step3");
            } catch (Exception e) {
                log.error("thread1 exception", e);
            }
        });
    }

    @Override
    @MyAnnotation
    public String aopTest() {
        log.debug("aop testing ...");
        return "aop result";
    }

    @SneakyThrows
    @Override
    public void synchronizedTest() {
        final int[] i = {0};
        for (int j = 0; j < 20; j++) {
            synchronized (i) {
                asyncServiceExecutor.execute(() -> {
                    log.debug("synchronizedTest:" + Thread.currentThread().getName() + ":i=" + i[0]++);
                });
            }
        }

        // 仅用作笔记
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> i[0]++);
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> i[0]++, asyncServiceExecutor);
        CompletableFuture.allOf(completableFuture1, completableFuture2);
        int j = completableFuture1.get();
        int k = completableFuture2.get();
        log.debug("completableFuture1:{},completableFuture2:{}", j, k);
    }

    @Override
    public void forkJoinTest() {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            forkJoinPool.execute(() -> log.debug("forkJoinTest:" + Thread.currentThread().getName() + ":i=" + finalI));
        }
    }

    @Override
    public void redisTest(String key, String value) {
        log.debug("set::redis key:{},redis value:{}", key, value);
        redisTemplate.boundValueOps("str" + key).set(value);
        redisTemplate.boundListOps("list" + key).leftPush(value);
        log.debug("set::redis key:{},redis value:{}", key, value);
    }

    @Override
    public void redisStreamTest(String key, String value) {
        log.debug("redis stream test");
        redisTemplate.boundStreamOps(key).add(new HashMap<String, String>() {
            {
                put("name", value);
            }
        });
        var res = redisTemplate.boundStreamOps(key).read(ReadOffset.from("0"));
        log.debug("stream read result{}", JSON.toJSONString(res));
    }

    @Override
    public void redisLuaTest(String key, String value) {
        // 注意 KEYS  ARGV 需要大写，否则报错
        String script = "redis.call('set', KEYS[1], ARGV[1]) local str = redis.call('get', KEYS[1])   return str";
        DefaultRedisScript<RedisLuaBean> defaultRedisScript = new DefaultRedisScript<>(script, RedisLuaBean.class);
        List<String> keys = List.of(key);
        String value1 = JSON.toJSONString(RedisLuaBean.builder()
                .id(1)
                .name("redis-lua" + value)
                .description("redis-lua-test" + value)
                .build());
        Object result = redisTemplate.execute(defaultRedisScript, keys, value1);
        log.debug("redis lua result:{}", JSON.toJSONString(result));
    }

    @Override
    public void redisTransactionTest(String key, String value) {
        log.debug("Transaction start::redis key:{},redis value:{}", key, value);
        redisTemplate.execute(new SessionCallback<List<Object>>() {
            @Override
            public <K, V> List<Object> execute(RedisOperations<K, V> operations) throws DataAccessException {
                redisTemplate.boundValueOps(key).set(value);
                redisTemplate.multi();
                redisTemplate.boundValueOps(key + "2").set(value + "==2");
                return redisTemplate.exec();
            }
        });
        log.debug("Transaction end::redis key:{},redis value:{}", key, value);
    }

    @Override
    public void redisPipeliningTest(String key, String value) {
        // RedisCallback需要处理底层逻辑，SessionCallback更高层的封装（使用较为友好）
        redisTemplate.executePipelined(new SessionCallback<>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                for (int i = 0; i < 100; i++) {
                    operations.boundListOps((K) key)
                            .rightPush((V) (i + SeperateConstant.SEPERATE_HG.getSeperate() + value + LocalDateTime.now()));
                }
                return null;
            }
        });
    }
}
