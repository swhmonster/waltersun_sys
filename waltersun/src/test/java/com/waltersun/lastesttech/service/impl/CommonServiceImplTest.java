package com.waltersun.lastesttech.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.waltersun.lastesttech.service.CommonService;

/**
 * @author walter
 * @date 2021-06-04 14:18
 */
@SpringBootTest
class CommonServiceImplTest {

    @Autowired
    private CommonService commonService;

    @Test
    void queryTest() {
        commonService.queryTest();
    }
}