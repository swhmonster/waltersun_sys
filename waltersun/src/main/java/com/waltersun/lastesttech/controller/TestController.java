package com.waltersun.lastesttech.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.waltersun.lastesttech.service.TestService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author walter
 * @date 2021-07-30 9:06
 */
@RestController
@Api(tags = "功能测试相关接口")
@RequestMapping("test")
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TestController {
    private final TestService testService;
    private final RedisTemplate redisTemplate;

    @SneakyThrows
    @GetMapping("queryTest")
    @ApiOperation(value = "查询测试", response = String.class)
    @ResponseBody
    public String queryTest() {
        return testService.queryTest();
    }

    @SneakyThrows
    @GetMapping("redisTest")
    @ApiOperation(value = "redis测试", response = String.class)
    @ResponseBody
    public String redisTest(@ApiParam(name = "key", value = "键", required = true)
                            @RequestParam String key,
                            @ApiParam(name = "value", value = "键值", required = true)
                            @RequestParam String value) {
        log.debug("set::redis key:{},redis value:{}", key, value);
        redisTemplate.boundValueOps("str" + key).set(value);
        redisTemplate.boundListOps("list" + key).leftPush(value);
        log.debug("set::redis key:{},redis value:{}", key, value);

        // redis stream test
        log.debug("redis stream test");
        redisTemplate.boundStreamOps(key).add(new HashMap<String,String>(){
            {
                put("name", value);
            }
        });
        wait(1);
        var res = redisTemplate.boundStreamOps(key).read(ReadOffset.lastConsumed());
        log.debug("stream read result{}", JSON.toJSONString(res));
        return StringUtils.EMPTY;
    }

    @SneakyThrows
    @GetMapping("redisTransactionTest")
    @ApiOperation(value = "redis事务测试", response = List.class)
    @ResponseBody
    public String redisTransactionTest(@ApiParam(name = "key", value = "键", required = true)
                                       @RequestParam String key,
                                       @ApiParam(name = "value", value = "键值", required = true)
                                       @RequestParam String value) {
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
        return StringUtils.EMPTY;
    }

    @SneakyThrows
    @GetMapping("jetcacheTest")
    @ApiOperation(value = "jetcache测试", response = String.class)
    @ResponseBody
    public String jetcacheTest(@ApiParam(name = "condition", value = "条件（可作为cache key）", required = true)
                               @RequestParam int condition) {
        return testService.jetcacheTest(condition);
    }

    @SneakyThrows
    @GetMapping("rocketmqTest")
    @ApiOperation(value = "rocketmq测试", response = String.class)
    @ResponseBody
    public String rocketmqTest(@ApiParam(name = "topic", value = "消息主题", required = true)
                               @RequestParam String topic,
                               @ApiParam(name = "msg", value = "具体消息", required = true)
                               @RequestParam String msg) {
        return testService.rocketmqTest(topic, msg);
    }

    @SneakyThrows
    @GetMapping("kafkaTest")
    @ApiOperation(value = "kafka测试", response = String.class)
    @ResponseBody
    public String kafkaTest(@ApiParam(name = "msg", value = "具体消息", required = true)
                            @RequestParam String msg) {
        return testService.kafkaTest(msg);
    }

    @SneakyThrows
    @GetMapping("profobufTest")
    @ApiOperation(value = "profobuf测试", response = String.class)
    @ResponseBody
    public byte[] profobufTest(@ApiParam(name = "type1", value = "序列化方式：KryoImpl,ProtocolImpl", required = true)
                            @RequestParam String type1,
                               @ApiParam(name = "type2", value = "1:序列化;2:反序列化", required = true)
                            @RequestParam String type2,
                            @ApiParam(name = "str", value = "随机数据", required = true)
                            @RequestParam String str) {
        return testService.SerializeTest(type1, type2, str);
    }
}
