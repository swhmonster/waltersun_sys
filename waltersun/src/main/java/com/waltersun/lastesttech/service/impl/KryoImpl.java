package com.waltersun.lastesttech.service.impl;

import com.waltersun.lastesttech.service.SerializationService;

/**
 * @author walter
 * @date 2021-08-17 11:17
 */
public class KryoImpl implements SerializationService {
    @Override
    public byte[] encoder(String str) {
        return new byte[0];
    }

    @Override
    public Object decoder(byte[] bytes) {
        return null;
    }
}
