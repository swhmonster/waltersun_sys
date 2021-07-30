package com.waltersun.lastesttech.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}