package com.waltersun.lastesttech.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.waltersun.lastesttech.service.TestService;

/**
 * @author walter
 * @date 2021-06-04 14:18
 */
@SpringBootTest
class TestServiceImplTest {

    @Autowired
    private TestService testService;

    @Test
    void queryTest() {
        testService.queryTest();
    }
}