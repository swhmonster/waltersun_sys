package com.waltersun.lastesttech.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waltersun.lastesttech.mapper.CommonMapper;
import com.waltersun.lastesttech.service.CommonService;

import lombok.RequiredArgsConstructor;

/**
 * @author walter
 * @date 2021-06-03 11:31
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CommonServiceImpl implements CommonService {
    private final CommonMapper commonMapper;

    @Override
    public String queryTest() {
        return commonMapper.queryTestLimit1();
    }
}
